package com.manhe.service.impl;

import com.manhe.dal.dao.CaseDAO;
import com.manhe.dal.dataobject.CaseDO;
import com.manhe.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("caseService")
public class CaseServiceImpl implements CaseService {


    @Autowired
    private CaseDAO dao;


    @Override
    public void insert(CaseDO caseDO) {
        dao.insert(caseDO);
    }

    @Override
    public void update(CaseDO caseDO) {
        dao.update(caseDO);
    }

    @Override
    public CaseDO get(Map<String, Object> params) {
        return dao.get(params);
    }

    @Override
    public void delete(Map<String, Object> params) {
        dao.delete(params);
    }

    @Override
    public List<CaseDO> find(Map req) {
        return dao.getListByMap(req);
    }
}
