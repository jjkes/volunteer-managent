package com.zj.auth.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/4 22:21
 */

public class UsernamePasswordAuthentication implements AuthenticationToken {

    private String token;

    public UsernamePasswordAuthentication(String token) {
        this.token = token;
    }



    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
