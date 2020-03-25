package com.manhe.service;

import com.manhe.dal.dataobject.RecommendDO;

import java.util.List;
import java.util.Map;

public interface RecommendService {
    public void insert(RecommendDO recommendDO);

    public void update(RecommendDO recommendDO);

    public RecommendDO get(Map<String, Object> params);

    public void delete(Map<String, Object> params);

    public List<RecommendDO> find(Map req);

}
