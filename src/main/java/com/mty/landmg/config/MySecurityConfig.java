package com.mty.landmg.config;

import com.alibaba.fastjson.JSONObject;
import com.mty.landmg.common.api.R;
import com.mty.landmg.common.enums.ResultCode;
import com.mty.landmg.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * SpringSecurity配置类
 *
 * @author york
 * @since 2025-02-18
 */
@EnableWebSecurity
@EnableMethodSecurity
public class MySecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * 认证管理器
     * 保证spring中只有一个 UserDetailsService，会自动加载， 这里我们将MemberServiceImpl实现了UserDetailsService
     * 所以这里的参数实际是MemberServiceImpl
     *
     * @param userDetailsService 提供用户信息的接口
     * @return 认证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        //用DelegatingPasswordEncoder就不需要单独指定了，如果用的是某个具体PasswordEncoder， 这里需要指定，参见上次代码
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    /**
     * 新版本安全过滤器链配置
     *
     * @param http 请求
     * @return 过滤器链
     * @throws Exception 认证异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(req ->
                        req.antMatchers("/public/**", "/auth/login/**","/v2/**","/doc.html",
                                        "/actuator/**", "/swagger-ui/**", "/swagger-ui.html",
                                        "/webjars/**", "/swagger-resources/**", "/v3/**", "/csrf").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(MySecurityConfig::myExceptionHandler)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * 自定义异常处理
     *
     * @param configurer 配置
     */
    private static void myExceptionHandler(ExceptionHandlingConfigurer<HttpSecurity> configurer) {
        configurer.accessDeniedHandler((request, response, accessDeniedException) -> {
            //无权限
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            //中文需要设置编码，防止乱码
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(JSONObject.toJSON(R.fail(ResultCode.UN_AUTHORIZED)));
        }).authenticationEntryPoint(((request, response, authException) -> {
            //认证失败
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(JSONObject.toJSON(R.fail(ResultCode.AUTHCATION_ERROR)));
        }));
    }

    /**
     * 加密方式
     * {@link org.springframework.security.crypto.factory.PasswordEncoderFactories}
     *
     * @return 加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 默认编码算法bcrypt
        var encodingId = "bcrypt";
        // 用map支持多种加密算法
        var encoders = Map.of(
                encodingId, new BCryptPasswordEncoder(),
                "MD5", new MessageDigestPasswordEncoder("MD5")
        );
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }

}
