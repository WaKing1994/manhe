package com.manhe.service;

import com.manhe.dal.dataobject.CaseCategoryDO;

import java.util.List;
import java.util.Map;

public interface CaseCategoryService {
    public void insert(CaseCategoryDO caseCategoryDO);

    public void update(CaseCategoryDO caseCategoryDO);

    public CaseCategoryDO get(Map<String, Object> params);

    public void delete(Map<String, Object> params);

    public List<CaseCategoryDO> find(Map req);

}
