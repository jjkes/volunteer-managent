package com.zj.sys.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/23 22:30
 */
@Configuration
@ConfigurationProperties(prefix = "redis.jedis.pool",ignoreUnknownFields = true)
public class RedisConfig {
    /**
     * redis地址
     */
    private String host = "localhost";
    /**
     * TODO
     */
    private int database = 0;

    private int port = 6379;

    private String password;

    private int maxActive = 8;

    private long maxWait = 1000;
    /**
     * 线程池中最大空闲连接数
     */
    private int maxIdle = 200;
    /**
     * 线程池最小空闲连接数
     */
    private int minIdle = 50;

    private int timeout = 1000;

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
