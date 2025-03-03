package com.mty.landmg.controller;


import com.mty.landmg.dto.AuthDTO;
import com.mty.landmg.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户管理
 *
 * @author york
 * @since 2025-02-18
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户管理")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean addUser(@RequestBody AuthDTO auth) {
        return userService.addUser(auth);
    }
}
