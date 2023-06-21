package com.zj.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 赵健
 * @version 1.0
 * @description: 读取配置文件中的内容：以volunteer开头的节点
 * @date 2023/3/10 16:20
 */
@Configuration
@ConfigurationProperties(prefix = "volunteer")
public class SysProperties {

    private String baseFilePath;

    public String getBaseFilePath() {
        return baseFilePath;
    }

    public void setBaseFilePath(String baseFilePath) {
        this.baseFilePath = baseFilePath;
    }

}
