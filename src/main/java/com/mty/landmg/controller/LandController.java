package com.mty.landmg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mty.landmg.dto.LandDTO;
import com.mty.landmg.entity.Land;
import com.mty.landmg.service.ILandService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 地块管理
 *
 * @author york
 * @since 2025-02-25
 */
@RestController
@RequestMapping("/land")
@Api(value = "地块管理" , tags = "地块控制类")
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

    /**
     * 修改地块信息
     *
     * @param landDTO 地块DTO
     * @return 是否修改成功
     */
    @PutMapping("/update")
    public boolean update(@RequestBody LandDTO landDTO) {
        Land land = landDTO.toLand();
        return landService.updateById(land);
    }

    /**
     * 分页查询地块信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页数量
     * @return 分页结果
     */
    @GetMapping("/listPage")
    public Page<Land> list(@RequestParam(defaultValue = "1") int pageNum,
                           @RequestParam(defaultValue = "10") int pageSize,
                           @RequestParam String remark) {
        Page<Land> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Land> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("remark",remark);
        return landService.page(page, queryWrapper);
    }
}
