package com.manhe.service;

import com.manhe.dal.dataobject.NewsDO;

import java.util.List;
import java.util.Map;

public interface NewsService {
    public void insert(NewsDO newsDO);

    public void update(NewsDO newsDO);

    public NewsDO get(Map<String, Object> params);

    public void delete(Map<String, Object> params);

    public List<NewsDO> find(Map req);

}
