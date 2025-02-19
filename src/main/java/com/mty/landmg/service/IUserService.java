package com.mty.landmg.service;

import com.mty.landmg.dto.AuthDTO;
import com.mty.landmg.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户表(Member)表服务接口
 *
 * @author york
 * @since 2025-02-18
 */
public interface IUserService extends IService<User> {

    boolean addUser(AuthDTO authDTO);
    String queryMemberNameByMemberCode(String memberCode);
}

