package com.mty.landmg.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * SpingDoc配置属性读取
 *
 * @author york
 * @since 2025-02-18
 */
@Data
//@Configuration
//@ConfigurationProperties(prefix = "doc-info")
public class DocInfoProperties {

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 作者
     */
    private String author;

    /**
     * 网站
     */
    private String website;

    /**
     * 联系方式：email
     */
    private String email;

    private String version;

}
