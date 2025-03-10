package com.mty.landmg.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * 演示接口
 *
 * @author york
 * @since 2025-02-18
 */
@RestController
@Slf4j
@Api(value = "权限测试",tags = "测试或权限检查//todo 权限检查改造-根据需求")
public class DemoController {

    /**
     * 主页，认证后访问
     *
     * @param authentication 认证信息
     * @return String
     */
    @GetMapping("/")
    public String index(Authentication authentication) {
        log.info("会将认证信息（Authentication）带过来：{}", JSON.toJSONString(authentication));
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String memberCode = (String)authentication.getPrincipal();
        return "This is Home Page! Time: " + LocalDateTime.now()+",[memberCode] "+memberCode+",[权限]:"+authorities.toString();
    }

    /**
     * 公共，在配置中忽略了该路径
     *
     * @param authentication 认证信息，因为被放行，所以为null
     * @return String
     */
    @GetMapping("/public")
    public String publicPage(Authentication authentication) {
        log.info("被忽略的接口没有认证信息：{}", JSON.toJSONString(authentication));
        return "This is Public Page! Time: " + LocalDateTime.now();
    }

    /**
     * 个人界面，需要认证，并且有USER权限
     *
     * @param authentication 认证信息
     * @return String
     */
    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public String profilePage(Authentication authentication) {
        log.info("认证信息：{}", JSON.toJSONString(authentication));
        return "This is Profile Page! Time: " + LocalDateTime.now();
    }

    /**
     * 管理界面，需要认证，并且有ADMIN权限
     *
     * @param authentication 认证信息
     * @return String
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminPage(Authentication authentication) {
        log.info("认证信息：{}", JSON.toJSONString(authentication));
        return "This is Admin Page! Time: " + LocalDateTime.now();
    }
}
