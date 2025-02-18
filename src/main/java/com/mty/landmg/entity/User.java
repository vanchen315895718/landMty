package com.mty.landmg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 用户表(Member)实体类
 *
 * @author york
 * @since 2025-02-18
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("land_mg.ums_member")
public class User implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "member_id", type = IdType.AUTO)
    private Long memberId;

    /**
     * 用户code（唯一）
     */
    @TableField("member_code")
    private String memberCode;
    /**
     * 用户姓名
     */
    @TableField("member_name")
    private String memberName;
    /**
     * 用户密码
     */
    @TableField("member_password")
    @JsonIgnore
    private String memberPassword;
    /**
     * 用户角色（多个逗号分隔）
     */
    @TableField("roles")
    private String roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(this.roles.split(","))
                .filter(item -> item != null && item.length() > 0)
                //用户需要 ROLE_ 开头
                .map(item -> "ROLE_" + item.trim().toUpperCase())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.memberPassword;
    }

    @Override
    public String getUsername() {
        return this.memberCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

