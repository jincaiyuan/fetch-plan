package com.walker.fetchplan.dal.mapper;

import com.walker.fetchplan.dal.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectById(Integer id);
}
