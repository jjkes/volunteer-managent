package com.zj.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.stereotype.Component;

/**
 * @author 赵健
 * @version 1.0
 * @description: 重写一个Http消息转化类，否则程序没法扫描出这个类，就会报异常
 * @date 2023/6/27 14:33
 */
@Component
public class MyConverterService extends HttpMessageConverters {
}
