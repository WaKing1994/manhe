package com.manhe.service.impl;

import com.manhe.dal.dao.AdminDAO;
import com.manhe.dal.dataobject.AdminDO;
import com.manhe.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Override
    public void insert(AdminDO adminDO) {
        dao.insert(adminDO);
    }

    @Override
    public AdminDO get(Map<String, Object> params) {
        return dao.get(params);
    }

    @Override
    public void delete(Map<String,Object> params) {
        dao.delete(params);
    }

    @Override
    public void update(AdminDO adminDO) {
        dao.update(adminDO);
    }

    @Override
    public List<AdminDO> findAdmin(Map req) {
        return dao.getListByMap(req);
    }


    @Autowired
    private AdminDAO dao;
}
