package com.manhe.dal.dao;


import com.manhe.dal.dataobject.AdminDO;
import com.manhe.dal.dataobject.ConfigDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConfigDAO {

    void insert(ConfigDO configDO);

    void update(ConfigDO configDO);

    void delete(Map<String, Object> params);

    ConfigDO get(Map<String, Object> params);

    List<ConfigDO> getListByMap(Map<String, Object> req);

}
