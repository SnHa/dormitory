package com.atsun.dormitory.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author HP
 */
@Getter
@Setter
@NoArgsConstructor
public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = 8523854101043037809L;

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
