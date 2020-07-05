package com.walker.fetchplan.dal.mapper;

import com.walker.fetchplan.dal.model.PlanDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * PlanDetailDAO继承基类
 */
@Mapper
public interface PlanDetailMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PlanDetail record);

    int insertSelective(PlanDetail record);

    PlanDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlanDetail record);

    int updateByPrimaryKey(PlanDetail record);

    PlanDetail selectByName(String planName);

    List<PlanDetail> selectList();

    List<PlanDetail> selectSubmittableList(Date date);
}