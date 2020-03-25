package com.manhe.service.impl;

import com.manhe.dal.dao.NewsDAO;
import com.manhe.dal.dataobject.NewsDO;
import com.manhe.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("newsService")
public class NewsServiceImpl implements NewsService {


    @Autowired
    private NewsDAO dao;


    @Override
    public void insert(NewsDO newsDO) {
        dao.insert(newsDO);
    }

    @Override
    public void update(NewsDO newsDO) {
        dao.update(newsDO);
    }

    @Override
    public NewsDO get(Map<String, Object> params) {
        return dao.get(params);
    }

    @Override
    public void delete(Map<String, Object> params) {
        dao.delete(params);
    }

    @Override
    public List<NewsDO> find(Map req) {
        return dao.getListByMap(req);
    }
}
