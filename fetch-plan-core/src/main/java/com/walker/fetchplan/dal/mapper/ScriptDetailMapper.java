package com.walker.fetchplan.dal.mapper;

import com.walker.fetchplan.dal.model.ScriptDetail;
import org.springframework.stereotype.Repository;

/**
 * ScriptDetailMapper继承基类
 */
@Repository
public interface ScriptDetailMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ScriptDetail record);

    int insertSelective(ScriptDetail record);

    ScriptDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScriptDetail record);

    int updateByPrimaryKey(ScriptDetail record);
}