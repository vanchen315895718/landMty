package com.mty.landmg.service.impl;

import com.mty.landmg.dto.AuthDTO;
import com.mty.landmg.entity.User;
import com.mty.landmg.mapper.UserMapper;
import com.mty.landmg.service.IUserService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户表(Member)表服务实现类
 *
 * @author york
 * @since 2025-02-18
 */
@Service("memberService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserDetailsService, IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("数据库认证：{}", username);
        User one = this.getOne(Wrappers.<User>lambdaQuery().eq(User::getMemberCode, username));
        log.debug("当前用户信息：{}", JSON.toJSONString(one));
        return one;
    }


    @Override
    public boolean addUser(AuthDTO authDTO) {
        return this.save(User.builder()
                .memberCode(authDTO.getCode())
                .memberName(authDTO.getMemberName())
                .memberPassword(passwordEncoder.encode(authDTO.getPwd()))
                .roles("USER")
                .build());
    }
}

