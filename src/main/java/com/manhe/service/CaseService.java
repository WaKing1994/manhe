package com.manhe.service;

import com.manhe.dal.dataobject.CaseDO;
import com.manhe.dal.pageUtils.PageInfo;

import java.util.List;
import java.util.Map;

public interface CaseService {
    public void insert(CaseDO caseDO);

    public void update(CaseDO caseDO);

    public CaseDO get(Map<String, Object> params);

    public void delete(Map<String, Object> params);

    public List<CaseDO> find(Map req);

    public List<CaseDO> find(Map req, PageInfo pageInfo);
}
