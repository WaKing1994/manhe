package com.manhe.service.impl;

import com.manhe.dal.dao.ContactDAO;
import com.manhe.dal.dataobject.ContactDO;
import com.manhe.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("contactService")
public class ContactServiceImpl implements ContactService {


    @Autowired
    private ContactDAO dao;


    @Override
    public void insert(ContactDO contactDO) {
        dao.insert(contactDO);
    }

    @Override
    public void update(ContactDO contactDO) {
        dao.update(contactDO);
    }

    @Override
    public ContactDO get(Map<String, Object> params) {
        return dao.get(params);
    }

    @Override
    public void delete(Map<String, Object> params) {
        dao.delete(params);
    }

    @Override
    public List<ContactDO> find(Map req) {
        return dao.getListByMap(req);
    }
}
