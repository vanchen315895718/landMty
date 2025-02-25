//package com.mty.landmg.service.impl;
//
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.mty.landmg.entity.User;
//import com.mty.landmg.service.IUserService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * Member测试
// *
// * @author york
// * @since 2025-02-18
// */
//@SpringBootTest
//@Slf4j
//public class MemberServiceImplTest {
//
//    @Autowired
//    private IUserService iUserService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//    @Test
//    public void queryMemberNameByMemberCode(){
//        String s = iUserService.queryMemberNameByMemberCode("user");
//        System.out.println(s);
//    }
//
//    /**
//     * 插入Member数据
//     */
//    @Test
//    public void insertMember() {
//        iUserService.save(User.builder()
//                .memberCode("user")
//                .memberName("用户李四").memberPassword(passwordEncoder.encode("user"))
//                .roles("USER")
//                .build());
//        iUserService.save(User.builder()
//                .memberCode("admin")
//                .memberName("管理员张三").memberPassword(passwordEncoder.encode("admin"))
//                .roles("ADMIN,USER")
//                .build());
//    }
//
//    /**
//     * 加密密码
//     */
//    @Test
//    public void insertMemberNew() {
//        String userEncode = passwordEncoder.encode("user");
//        String adminEncode = passwordEncoder.encode("admin");
//        log.info(userEncode);
//        log.info(adminEncode);
//        iUserService.update(Wrappers.<User>lambdaUpdate()
//                .set(User::getMemberPassword, userEncode).eq(User::getMemberCode, "user"));
//        iUserService.update(Wrappers.<User>lambdaUpdate()
//                .set(User::getMemberPassword, adminEncode).eq(User::getMemberCode, "admin"));
//    }
//}