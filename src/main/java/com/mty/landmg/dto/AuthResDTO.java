package com.mty.landmg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 认证结果DTO
 *
 * @author york
 * @since 2025-02-18
 */
@Data
@Builder
@AllArgsConstructor
public class AuthResDTO {

    /**
     * 账号
     */
    private String code;

    /**
     * Jwt Token
     */
    private String token;

}
