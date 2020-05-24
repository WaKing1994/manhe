package com.manhe.service.impl;

import com.manhe.dal.dao.AdminDAO;
import com.manhe.dal.dao.ConfigDAO;
import com.manhe.dal.dataobject.AdminDO;
import com.manhe.dal.dataobject.ConfigDO;
import com.manhe.service.AdminService;
import com.manhe.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {


    @Autowired
    private ConfigDAO dao;

    @Override
    public void insert(ConfigDO configDO) {
        dao.insert(configDO);
    }

    @Override
    public ConfigDO get(Map<String, Object> params) {
        return dao.get(params);
    }

    @Override
    public void delete(Map<String, Object> params) {
        dao.delete(params);
    }

    @Override
    public void update(ConfigDO configDO) {
        dao.update(configDO);
    }

    @Override
    public List<ConfigDO> find(Map req) {
        return dao.getListByMap(req);
    }
}
