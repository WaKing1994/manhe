package com.manhe.dal.dao;

import com.manhe.dal.dataobject.NewsDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsDAO {

    void insert(NewsDO newsDO);

    void update(NewsDO newsDO);

    void delete(Map<String, Object> params);

    NewsDO get(Map<String, Object> params);

    List<NewsDO> getListByMap(Map<String, Object> req);

}
