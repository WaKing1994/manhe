package com.manhe.dal.dao;

import com.manhe.dal.dataobject.CaseDO;
import com.manhe.dal.pageUtils.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaseDAO {

    void insert(CaseDO caseDO);

    void update(CaseDO caseDO);

    void delete(Map<String, Object> params);

    CaseDO get(Map<String, Object> params);

    List<CaseDO> getListByMap(Map<String, Object> req);

    List<CaseDO> getListByMap(Map<String, Object> req, PageInfo pageInfo);

}
