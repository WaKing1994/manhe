package com.manhe.dal.dao;


import com.manhe.dal.dataobject.AdminDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminDAO {

    void insert(AdminDO adminDO);

    void update(AdminDO adminDO);

    void delete(Map<String, Object> params);

    AdminDO get(Map<String, Object> params);

    List<AdminDO> getListByMap(Map<String, Object> req);

}
