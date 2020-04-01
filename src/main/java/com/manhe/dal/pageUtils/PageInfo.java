package com.manhe.dal.pageUtils;


import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;

/**
 * 分页信息对象,从RowBounds继承是为了支持mybatis分页，因为mybatis内部分页是采用RowBounds，
 * 参见PaginationInterceptor中的RowBounds其实是pageInfo
 * @author  walking
 */
public class PageInfo extends RowBounds implements Serializable {

    public static final int DEFAULT_PAGE_NO = 1;//第一页从1开始，不是从0
    public static final int DEFAULT_PAGE_SIZE = 20;

    public static final int DEFAULT_PAGE_OFFSET = 0;// 默认偏移量0
    public static final int DEFAULT_PAGE_ROWS = 20;

    /**pageNo = -1默认查全部的*/
    public static final int PAGE_NO_ALL = -1;

    /**标准分页*/
    public static final int PAGE_LIMIT_TYPE_ONE = 1;
    /**偏移量分页*/
    public static final int PAGE_LIMIT_TYPE_TWO = 2;

    /** 当前页 */
    private Integer pageNo;
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    private Integer pageOffset;
    private Integer pageRows = DEFAULT_PAGE_ROWS;

    /** 总页数,-1区分其无记录，还是没有填充总页数*/
    private Integer totalPage = -1;
    /**总记录数*/
    private Integer totalCount = -1;

    // -------------------------------- 以下为sql中关键字 -------------------------------- //

    public static final CharSequence FROM = "from";

    public static final CharSequence LIMIT = "limit";

    public static final CharSequence GROUPBY = "group by";

    public static final CharSequence UNION = "union";

    public static final CharSequence OFFSET = "offset";

    public static final CharSequence ORDERBY = "order by";

    public static final String DESC = "desc";
    public static final String WHERE = "where";

    public static final String AND = "and";

    public static final String COUNT = "count__";




    @Override
    public String toString() {
        return "PageInfo [pageNo=" + pageNo + ", pageSize=" + pageSize + ", pageOffset=" + pageOffset + ", pageRows="
                + pageRows + ", totalPage=" + totalPage + ", totalCount=" + totalCount
                + "]";
    }


    // -------------------------------- 以下为Getter/Setter方法 -------------------------------- //


    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(Integer pageOffset) {
        this.pageOffset = pageOffset;
    }

    public Integer getPageRows() {
        return pageRows;
    }

    public void setPageRows(Integer pageRows) {
        this.pageRows = pageRows;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }


    public Integer getTotalCount() {
        return totalCount;
    }


    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }


    /**
     * 是否还有下一页
     * @return
     * @author walking
     */
    public boolean hasNextPage() {
        if (pageNo < totalPage || (pageNo <= 1 && totalCount == -1)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 翻到下一页
     * @return 返回页号
     * @author walking
     */
    public int moveToNext() {
        if (hasNextPage()) {
            pageNo++;
        }
        return pageNo;
    }


    /**
     * 翻到最后一页
     * @return 返回上页
     * @author walking
     */
    public void moveToLast () {
        pageNo = this.totalPage;
    }

    /**
     * 移到第一页
     *
     * @author walking
     */
    public void moveToFirst() {
        pageNo = DEFAULT_PAGE_NO;
    }

    /**
     * 是否还有上一页
     * @return
     * @author walking
     */
    public boolean hasPrevPage() {
        if (pageNo <= DEFAULT_PAGE_NO) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * 移到上一页
     * @return 返回页号
     * @author zhangyz created on 2013-4-11
     */
    public int moveToPrev() {
        pageNo--;
        return pageNo;
    }




    /**
     * 构造滚动型PageInfo
     * @param offset
     * @param rows
     * @return
     */
    public static PageInfo genPageInfoRoll(Integer offset, Integer rows) {
        PageInfo pageInfo = new PageInfo();
        //不传，默认从0开始
        if(offset == null){
            offset = DEFAULT_PAGE_OFFSET;
        }
        //不传，默认查20
        if(rows == null){
            rows = DEFAULT_PAGE_ROWS;
        }
        pageInfo.setPageOffset(offset);
        pageInfo.setPageRows(rows);
        return pageInfo;
    }


    /**
     * 构造翻页型PageInfo
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static PageInfo genPageInfoPage(Integer pageNo, Integer pageSize) {
        //不传默认从1开始
        if(pageNo == null){
            pageNo = DEFAULT_PAGE_NO;
        }
        if(pageSize == null){
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return new PageInfo(pageNo, pageSize);
    }

    /**
     * 构造翻页型PageInfo,不传pageNo 默认从1开始
     * @param pageSize
     * @return
     */
    public static PageInfo genPageInfoPage(Integer pageSize) {
       return genPageInfoPage(null, pageSize);
    }


    private PageInfo(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }


    private PageInfo() {
    }

}
