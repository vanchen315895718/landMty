package com.mty.landmg.controller;

import com.mty.landmg.dto.AuthDTO;
import com.mty.landmg.dto.AuthResDTO;
import com.mty.landmg.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * 认证接口
 *
 * @author york
 * @since 2025-02-18
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    /**
     * 基于密码认证
     *
     * @return 认证结果
     */
    @PostMapping("/login")
    public AuthResDTO authByPassword(@RequestBody AuthDTO auth) {
        var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getCode(), auth.getPwd()));
        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
        return new AuthResDTO(auth.getCode(), jwtUtil.createToken(auth.getCode(), Map.of("roles", AuthorityUtils.authorityListToSet(userDetails.getAuthorities()))));
    }

    //todo token接入redis后实现退出功能

}
