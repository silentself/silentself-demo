package com.atdyl.mybatisplus.config.generator;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dong YL
 * @version V1.0 2020/1/4 15:41
 */
@Data
@ConfigurationProperties(prefix = "generator")
@Configuration
public class CodeGeneratorConfig {

    //模块名
    private String moduleName;

    //生成的文件所在的包名
    private String parent;

    //包含的表明
    private String[] include;

    //去掉的前缀
    private String stripPrefix;
}
