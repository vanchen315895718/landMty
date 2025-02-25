package com.mty.landmg.service.impl;

import com.mty.landmg.entity.Land;
import com.mty.landmg.service.ILandService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class LandServiceTest {
    @Autowired
    private ILandService landService;

    @Test
    public void add(){
        Land land = Land.builder().geom("{ \"type\": \"Point\", \"coordinates\": [30, 10] }")
                        .remark("备注").build();
        System.out.println(landService.save(land));
        System.out.println(land.toString());
    }

    @Test
    public void query(){
        Land byId = landService.getById(6L);
        System.out.println(byId.toString());
    }
}
