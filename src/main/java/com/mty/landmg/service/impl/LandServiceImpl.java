package com.mty.landmg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mty.landmg.entity.Land;
import com.mty.landmg.mapper.LandMapper;
import com.mty.landmg.service.ILandService;
import org.springframework.stereotype.Service;

@Service("landService")
public class LandServiceImpl extends ServiceImpl<LandMapper, Land> implements ILandService {
}
