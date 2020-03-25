package com.manhe.dal.dao;

import com.manhe.dal.dataobject.ContactDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ContactDAO {

    void insert(ContactDO contactDO);

    void update(ContactDO contactDO);

    void delete(Map<String, Object> params);

    ContactDO get(Map<String, Object> params);

    List<ContactDO> getListByMap(Map<String, Object> req);

}
