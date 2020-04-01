package com.manhe.intercept;

import com.manhe.common.StringUtils;
import com.manhe.dal.pageUtils.MbiUtils;
import com.manhe.dal.pageUtils.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * @Author walking
 * @Description 分页拦截器，主要处理1，得到结果集总数。2，拼接limit 进行分页
 * @Date 17:48 2019-12-03
 * @Param
 * @return
 **/
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
@Slf4j
@Component
public class PageMybatisInterceptor implements Interceptor, ApplicationContextAware {


    /**
     * @Author walking
     * @Description 注入拦截器，使其生效
     * @Date 16:01 2019-12-05
     * @Param [applicationContext]
     * @return void
     **/
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContext SPRING_CONTEXT = applicationContext;
        try{
            Class<SqlSessionFactory> clazz = (Class<SqlSessionFactory>) Class.forName(SqlSessionFactory.class.getName());
            Map<String, SqlSessionFactory> sqlSessionFactoryMap = SPRING_CONTEXT.getBeansOfType(clazz);
            if(sqlSessionFactoryMap!=null && sqlSessionFactoryMap.size()>0){
                for(SqlSessionFactory ssf : sqlSessionFactoryMap.values()){
                    ssf.getConfiguration().addInterceptor(this);
                }
            }
        }catch (Throwable t){
            log.warn("This application do not have SqlSessionFactory, do not config ZanTestInterceptor");
        }
    }



    /**
     * 拦截后要执行的方法，
     * 在拦截之前，主要进行改造sql
     * 1,对于偏移量分页，不查询结果集总数,只进行拦截，添加limit.
     * 2,对于标准分页，查询结果集总数，总页数，并返回填充到pageInfo。并且修改sql添加limit,进行物理分页
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //log.info("进入分页拦截器");
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MbiUtils.getMetaObject(statementHandler);

        Object  obj = metaStatementHandler.getValue("delegate.rowBounds");

        if(obj == null){
            //log.info("pageinfo is null");
            return invocation.proceed();
        }
        RowBounds rowBounds = (RowBounds) obj;
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

        //log.info("sql:{}", sql);
        if (rowBounds != null && rowBounds instanceof PageInfo) {
            //log.info("是否真正拦截");


            PageInfo pageInfo = (PageInfo) rowBounds;
            //当传偏移量时，其实并不需要获得结果集总数
            //当传标准分页时，前端需要拿到总结果集数量，做分页。
            Integer limitType = PageInfo.PAGE_LIMIT_TYPE_TWO;
            if (pageInfo.getPageOffset() == null && pageInfo.getPageNo() != null) {
                limitType = PageInfo.PAGE_LIMIT_TYPE_ONE;
                this.setTotalRecord(pageInfo,
                        invocation, sql);
            }

            String pageSql = this.getMysqlPageSql(pageInfo, sql, limitType);
            //把客户端提交的sql语句替换成分页sql语句
            metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
            //log.info("带分页的sql,{}", pageSql);

            //已做物理分页，原内存分页不需要
            metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
            metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
        }
        return invocation.proceed();
    }


    /**
     * @return java.lang.Object
     * @Author walking
     * @Description 获取代理对象
     * @Date 15:43 2019-12-04
     * @Param [target]
     **/
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * @return void
     * @Author walking
     * @Description 设置对象参数
     * @Date 15:43 2019-12-04
     * @Param [properties]
     **/
    @Override
    public void setProperties(Properties properties) {
//        this.dbType = properties.getProperty("dbType", "mysql");
    }


    /**
     * 获取Mysql数据库的分页查询语句
     *
     * @param page 分页对象
     * @param sql  原sql string
     * @return Mysql数据库分页语句
     */
    private String getMysqlPageSql(PageInfo page, String sql, Integer limitType) {
        //计算第一条记录的位置，Mysql中记录的位置是从0开始的。
        int offset = PageInfo.DEFAULT_PAGE_OFFSET;
        StringBuilder sqlBuffer = new StringBuilder(sql);
        if (limitType == PageInfo.PAGE_LIMIT_TYPE_ONE) {
            offset = (page.getPageNo() - 1) * page.getPageSize();
            sqlBuffer.append(" limit ").append(offset).append(",").append(page.getPageSize());
        } else {
            offset = page.getPageOffset();
            sqlBuffer.append(" limit ").append(offset).append(",").append(page.getPageRows());
        }
        return sqlBuffer.toString();
    }


    /**
     * 给当前的参数对象page设置总记录数,总页数
     *
     * @param page Mapper映射语句对应的参数对象
     */
    private void setTotalRecord(PageInfo page, Invocation invocation, String sql) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String countSql = this.getCountSql(sql);
        try {
            Connection connection = (Connection) invocation.getArgs()[0];
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            pstmt = connection.prepareStatement(countSql);
            statementHandler.parameterize(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int totalCount = rs.getInt(1);
                page.setTotalCount(totalCount);
                Integer totalPage = totalCount % page.getPageSize();
                if (totalPage != 0) {
                    page.setTotalPage(totalCount / page.getPageSize() + 1);
                } else {
                    page.setTotalPage(totalCount / page.getPageSize());
                }
            }
        } catch (Exception e) {
            log.info("执行统计sql时，执行失败，失败sql: {}", countSql);
            log.error("执行统计sql时，执行失败，失败原因: {}", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                log.error("close pstmt or rs error ext: {}", e);
            }
        }
    }


    /**
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     *
     * @param querySql 原sql
     * @return
     */
    private String getCountSql(String querySql) {
        querySql = querySql.replaceAll("[\\t\\n\\r]", " ");
        int index = -1;
        int unionIndex = StringUtils.indexOfWholeIgnoreCase(querySql, PageInfo.UNION);// union字句

        index = StringUtils.indexOfWholeIgnoreCase(querySql, PageInfo.FROM);

        String oriSelectClause = querySql.substring(0, index); //原始select子句

        //生成from开始的SQL语句。去除结尾的limit、order by，用于汇总查询
        String endSql = querySql.substring(index);


        // 防止原来语句中有limit
        index = StringUtils.lastIndexOfWholeIgnoreCase(endSql, PageInfo.LIMIT);
        if (index > 0) {
            endSql = endSql.substring(0, index);
        }
        // 防止原来语句中有order by
        index = StringUtils.lastIndexOfWholeIgnoreCase(endSql, PageInfo.ORDERBY);
        if (index > 0) {
            endSql = endSql.substring(0, index);
        }

        // 判断最后一个and和group by谁在后，主要考虑存在SELECT * FROM `user_score_detail`  where id in (select id from user_score GROUP by id ) and  id > 0类似sql,若出现这个sql,直接将endsql当作原表处理，不需当作子表处理
        //但考虑到存在select * from a group by mark having mark > 4 and mark < 10，故直接认为若sql存在group by ,即将endSql当作子表处理
        int andIndex = -1;
        //andIndex = StringUtils.lastIndexOfWholeIgnoreCase(endSql, PageInfo.AND);
        index = StringUtils.lastIndexOfWholeIgnoreCase(endSql, PageInfo.GROUPBY);


        //生成汇总的select子句
        StringBuilder selectClauseSb = new StringBuilder("SELECT COUNT(1) as " + PageInfo.COUNT);


        //汇总SQL添加from子句。如果原SQL不是group by sql，直接添加endSql。否则将原语句去除limit、group by子句后的整个SQL作为子表。 update by lsh 2017/9/9
        String sql;
        if (index > andIndex || unionIndex > 0) { //包含group by或union子句
            sql = selectClauseSb.append(" from (").append(oriSelectClause).append(endSql).append(") tmp_count_tbl").toString();
        } else {
            sql = selectClauseSb.append(" ").append(endSql).toString();
        }

        return sql;
    }


//    public static void main(String[] args) {
//        //String sql = "select * from a group by mark having mark > 4 and mark < 10";
//        String sql = "SELECT * FROM `user_score_detail`  where id in (select id from user_score GROUP by id ) and  id > 0";
//        System.out.println(getCountSql(sql));
//    }

}
