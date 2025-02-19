package com.mty.landmg.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("land_mg.jwt_record")
public class JwtRecord {
    @TableId
    private long id;
    private String jwt;
    private int isDeleted;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;

}
