package com.manhe.service.impl;

import com.manhe.dal.dao.CaseCategoryDAO;
import com.manhe.dal.dataobject.CaseCategoryDO;
import com.manhe.dal.pageUtils.PageInfo;
import com.manhe.service.CaseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("caseCategoryService")
public class CaseCategoryServiceImpl implements CaseCategoryService {


    @Autowired
    private CaseCategoryDAO dao;

    @Override
    public void insert(CaseCategoryDO caseCategoryDO) {
        dao.insert(caseCategoryDO);
    }

    @Override
    public void update(CaseCategoryDO caseCategoryDO) {
        dao.update(caseCategoryDO);
    }

    @Override
    public CaseCategoryDO get(Map<String, Object> params) {
        return dao.get(params);
    }

    @Override
    public void delete(Map<String, Object> params) {
        dao.delete(params);
    }

    @Override
    public List<CaseCategoryDO> find(Map req) {
        return dao.getListByMap(req);
    }

    @Override
    public List<CaseCategoryDO> find(Map req, PageInfo pageInfo) {
        return dao.getListByMap(req, pageInfo);
    }
}
