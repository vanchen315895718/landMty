package com.mty.landmg.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.login.LoginContext;
import java.util.Collection;
import java.util.HashMap;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final HashMap<String, String> map = new HashMap<>();

    public CustomAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public String getInfo(String key) {
        return this.map.getOrDefault(key,"");
    }

    public void putInfo(String key, String value) {
        this.map.put(key, value);
    }

}
