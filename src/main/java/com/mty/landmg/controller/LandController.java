package com.mty.landmg.controller;

import com.mty.landmg.dto.LandDTO;
import com.mty.landmg.service.ILandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author york
 * @since 2025-02-25
 */
@RestController
@RequestMapping("/land")
public class LandController {
    @Autowired
    private ILandService landService;

    @PostMapping("/add")
    public boolean add(@RequestBody LandDTO landDTO) {
        return landService.save(landDTO.toLand());
    }

    @DeleteMapping("/del/{id}")
    public boolean del(@RequestParam("id") Long id) {
        return landService.removeById(id);
    }
}
