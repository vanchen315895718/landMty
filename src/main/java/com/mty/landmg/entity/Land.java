package com.mty.landmg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mty.landmg.config.GeometryTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * 地块表实体类
 *
 * @author york
 * @since 2025-02-25
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "land_mg.tbl_land",autoResultMap = true)
public class Land {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "geom",typeHandler = GeometryTypeHandler.class)
    private String geom;

    @TableField("remark")
    private String remark;
}
