package com.manhe.service.impl;

import com.manhe.dal.dao.RecommendDAO;
import com.manhe.dal.dataobject.RecommendDO;
import com.manhe.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("recommendService")
public class RecommendServiceImpl implements RecommendService {


    @Autowired
    private RecommendDAO dao;


    @Override
    public void insert(RecommendDO recommendDO) {
        dao.insert(recommendDO);
    }

    @Override
    public void update(RecommendDO recommendDO) {
        dao.update(recommendDO);
    }

    @Override
    public RecommendDO get(Map<String, Object> params) {
        return dao.get(params);
    }

    @Override
    public void delete(Map<String, Object> params) {
        dao.delete(params);
    }

    @Override
    public List<RecommendDO> find(Map req) {
        return dao.getListByMap(req);
    }
}
