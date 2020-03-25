package com.manhe.dal.dao;

import com.manhe.dal.dataobject.CaseCategoryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaseCategoryDAO {

    void insert(CaseCategoryDO caseCategoryDO);

    void update(CaseCategoryDO caseCategoryDO);

    void delete(Map<String, Object> params);

    CaseCategoryDO get(Map<String, Object> params);

    List<CaseCategoryDO> getListByMap(Map<String, Object> req);

}
