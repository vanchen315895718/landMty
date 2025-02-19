package com.mty.landmg.filter;

import cn.hutool.core.util.ObjectUtil;
import com.mty.landmg.entity.JwtRecord;
import com.mty.landmg.mapper.JwtRecordMapper;
import com.mty.landmg.util.JwtUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 校验token，成功则认证通过
 *
 * @author york
 * @since 2025-02-18
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtRecordMapper jwtRecordMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = getToken(request);
        var claims = jwtUtil.parseClims(token);
        long jwtId = (long) claims.get("jwtId");
        if (ObjectUtil.isEmpty(jwtId)) {
            throw new BadCredentialsException("无效Token");
        }
        JwtRecord jwtRecord = jwtRecordMapper.selectById(jwtId);
        if (ObjectUtil.isEmpty(jwtRecord)
                || jwtRecord.getIsDeleted() != 0) {
            throw new BadCredentialsException("无效Token");
        }

        //认证通过
        SecurityContextHolder.getContext().setAuthentication(createAuthentication(claims));

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authenticationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        return authenticationHeader == null || authenticationHeader.startsWith("/auth");
    }

    private Authentication createAuthentication(Map<String, Object> claims) {
        var roles = JSON.parseArray(JSON.toJSONString(claims.get("roles")), String.class)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        //认证通过：通过这个三个参数的方法放行
        CustomAuthenticationToken customAuthenticationToken = new CustomAuthenticationToken(claims.get(Claims.SUBJECT), null, roles);
        customAuthenticationToken.putInfo("jwtId", claims.getOrDefault("jwtId", "") + "");
        return customAuthenticationToken;
    }

    /**
     * 从request中获取token，token在request中一般让前端放入header中
     * Authorization: Bearer xxxtokenxxx
     *
     * @param request 请求
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(auth -> auth.startsWith("Bearer "))
                .map(auth -> auth.replace("Bearer ", ""))
                .orElseThrow(() -> new BadCredentialsException("无效Token"));
    }

}
