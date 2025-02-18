package com.mty.landmg.config;


import com.mty.landmg.common.properties.DocInfoProperties;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * SpringDoc配置
 * <a href="https://www.cnblogs.com/xiao2/p/15621730.html">参考1</a>
 * <a href="https://mp.weixin.qq.com/s/scityFhZp9BOJorZSdCG0w">参考2</a>
 * <a href="https://zhuanlan.zhihu.com/p/529772728">参考3</a>
 *
 * @author york
 * @since 2025-02-18
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("unused")
public class SpringDocConfig {

    private final DocInfoProperties docInfo;

    @Bean
    public OpenAPI springDocOpenApi() {
        return new OpenAPI()
                //.externalDocs(externalDocumentation())
                //.addServersItem(server())
                .info(info());
    }

    /**
     * 文档信息
     *
     * @return Info
     */
    private Info info() {
        return new Info()
                .title(docInfo.getTitle())
                .description(docInfo.getDescription())
                .version(docInfo.getVersion())
                .contact(contact())
                .license(license());
    }

    /**
     * 联系方式
     *
     * @return Contact
     */
    private Contact contact() {
        Contact contact = new Contact();
        contact.setName(docInfo.getAuthor());
        contact.setEmail(docInfo.getEmail());
        contact.setUrl(docInfo.getWebsite());
        return contact;
    }

    /**
     * 协议
     *
     * @return License
     */
    private License license() {
        return new License()
                .name("Apache-2.0")
                .url("https://opensource.org/licenses/Apache-2.0");
    }

    /**
     * 服务信息定义
     *
     * @return Server
     */
    private Server server() {
        return new Server()
                .description("服务描述")
                .url("服务地址");
    }

    /**
     * 外部文档信息
     *
     * @return ExternalDocumentation
     */
    private ExternalDocumentation externalDocumentation() {
        return new ExternalDocumentation()
                .description("百万小破站")
                .url(docInfo.getWebsite());
    }
}
