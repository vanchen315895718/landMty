//package com.mty.landmg.service.impl;
//
//import entity.com.mty.landmg.Member;
//import service.com.mty.landmg.IMemberService;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
//    private IMemberService memberService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    /**
//     * 插入Member数据
//     */
//    @Test
//    public void insertMember() {
//        memberService.save(Member.builder()
//                .memberCode("user")
//                .memberName("用户李四").memberPassword("{noop}user")
//                .roles("USER")
//                .build());
//        memberService.save(Member.builder()
//                .memberCode("admin")
//                .memberName("管理员张三").memberPassword("{noop}admin")
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
//        memberService.update(Wrappers.<Member>lambdaUpdate()
//                .set(Member::getMemberPassword, userEncode).eq(Member::getMemberCode, "user"));
//        memberService.update(Wrappers.<Member>lambdaUpdate()
//                .set(Member::getMemberPassword, adminEncode).eq(Member::getMemberCode, "admin"));
//    }
//}