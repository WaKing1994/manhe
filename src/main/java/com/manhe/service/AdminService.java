package com.manhe.service;



import com.manhe.dal.dataobject.AdminDO;

import java.util.List;
import java.util.Map;

public interface AdminService {
    public void insert(AdminDO adminDO);

    public AdminDO get(Map<String, Object> params);

    public void delete(Map<String, Object> params);

    public void update(AdminDO adminDO);

    public List<AdminDO> findAdmin(Map req);

}
