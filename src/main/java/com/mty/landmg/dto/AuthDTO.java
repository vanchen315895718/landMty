package com.mty.landmg.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 认证信息DTO
 *
 * @author york
 * @since 2025-02-18
 */
@Data
@Builder
public class AuthDTO {

    /**
     * 账号
     */
    private String code;

    /**
     * 密码
     */
    private String pwd;
    
}
