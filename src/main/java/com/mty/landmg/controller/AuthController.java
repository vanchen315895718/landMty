package com.mty.landmg.controller;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.mty.landmg.common.api.R;
import com.mty.landmg.dto.AuthDTO;
import com.mty.landmg.dto.AuthResDTO;
import com.mty.landmg.entity.User;
import com.mty.landmg.filter.CustomAuthenticationToken;
import com.mty.landmg.util.JwtUtil;
import com.mty.landmg.util.RedisUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 认证接口
 *
 * @author york
 * @since 2025-02-18
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(value = "鉴权",tags = "登录控制相关")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    private static SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator();

    /**
     * 基于密码认证
     *
     * @return 认证结果
     */
    @PostMapping("/login")
    public AuthResDTO authByPassword(@RequestBody AuthDTO auth) {
        var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getCode(), auth.getPwd()));
        User userDetails = (User) authenticate.getPrincipal();

        Long jwtId = snowflakeGenerator.next();
        Map<String, Object> claims = new java.util.HashMap<>(Map.of("roles", AuthorityUtils.authorityListToSet(userDetails.getAuthorities())
                , "id", userDetails.getMemberId()));
        claims.put("jwtId", jwtId);

        String jwtToken = jwtUtil.createToken(auth.getCode(), claims);
        RedisUtils.set("jwt:" + jwtId, jwtToken,300);

        return new AuthResDTO(auth.getCode(),
                jwtToken);
    }


    @PostMapping("/logout")
    public R logout(Authentication authentication) {
        CustomAuthenticationToken customAuthenticationToken = (CustomAuthenticationToken) authentication;
        long jwtId = Long.parseLong(customAuthenticationToken.getInfo("jwtId"));
        RedisUtils.del("jwt:" + jwtId);
        return R.success("退出登录成功");
    }
}
