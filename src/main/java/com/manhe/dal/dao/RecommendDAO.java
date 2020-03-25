package com.manhe.dal.dao;

import com.manhe.dal.dataobject.RecommendDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RecommendDAO {

    void insert(RecommendDO recommendDO);

    void update(RecommendDO recommendDO);

    void delete(Map<String, Object> params);

    RecommendDO get(Map<String, Object> params);

    List<RecommendDO> getListByMap(Map<String, Object> req);

}
