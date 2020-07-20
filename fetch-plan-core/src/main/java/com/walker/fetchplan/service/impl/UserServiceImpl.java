package com.walker.fetchplan.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.walker.fetchplan.dal.mapper.UserMapper;
import com.walker.fetchplan.dal.model.User;
import com.walker.fetchplan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public String getToken(User user) {
        Integer id = user.getId();
        String password = user.getPassword();
        String token;
        token = JWT.create().withAudience(id.toString()).sign(Algorithm.HMAC256(password));
        return token;
    }
}
