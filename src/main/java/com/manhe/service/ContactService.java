package com.manhe.service;

import com.manhe.dal.dataobject.ContactDO;

import java.util.List;
import java.util.Map;

public interface ContactService {
    public void insert(ContactDO contactDO);

    public void update(ContactDO contactDO);

    public ContactDO get(Map<String, Object> params);

    public void delete(Map<String, Object> params);

    public List<ContactDO> find(Map req);

}
