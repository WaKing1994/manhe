package com.manhe.service;



import com.manhe.dal.dataobject.AdminDO;
import com.manhe.dal.dataobject.ConfigDO;

import java.util.List;
import java.util.Map;

public interface ConfigService {
    public void insert(ConfigDO configDO);

    public ConfigDO get(Map<String, Object> params);

    public void delete(Map<String, Object> params);

    public void update(ConfigDO configDO);

    public List<ConfigDO> find(Map req);

}
