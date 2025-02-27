package com.mty.landmg.dto;

import com.mty.landmg.entity.Land;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandDTO {
    private Long id;
    private String geom;
    private String remark;

    public Land toLand(){
        return Land.builder().id(this.id).geom(this.geom).remark(this.remark).build();
    }
}
