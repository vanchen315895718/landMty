package com.mty.landmg.service.impl;

import com.mty.landmg.entity.User;
import com.mty.landmg.mapper.UserMapper;
import com.mty.landmg.service.IUserService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("数据库认证：{}", username);
        User one = this.getOne(Wrappers.<User>lambdaQuery().eq(User::getMemberCode, username));
        log.info("当前用户信息：{}", JSON.toJSONString(one));
        return one;
    }

}

