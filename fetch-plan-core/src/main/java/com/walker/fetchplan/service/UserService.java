package com.walker.fetchplan.service;

import com.walker.fetchplan.dal.model.User;

public interface UserService {

    User getUserById(Integer id);

    String getToken(User user);
}
