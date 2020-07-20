package com.walker.fetchplan.dal.model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;

    public String getToken() {
        String token = "";
        token = JWT.create().withAudience(id.toString()).sign(Algorithm.HMAC256(password));
        return token;
    }
}
