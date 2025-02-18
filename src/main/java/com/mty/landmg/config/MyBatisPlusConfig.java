package com.mty.landmg.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus配置文件
 *
 * @author york
 * @since 2025-02-18
 */
@Configuration
@MapperScan("com.mty.landmg.mapper")
public class MyBatisPlusConfig {
}