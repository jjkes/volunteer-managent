package com.zj.common.annotations;

import com.zj.common.enums.AuthEnum;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface Authentication {
    /**
     * type 权限类型
     * 0：无须鉴权
     * 1：需要验证权限枚举
     * 2：需要验证用户权限字符串和用户对该资源的权限
     * @author 赵健
     * @date 2023/6/27 9:54
     */
    int type() default 1;
    /**
     * 权限枚举
     * @author 赵健
     * @date 2023/6/27 9:57
     */
    AuthEnum[] auth() default {};
    /**
     * 接口的权限介绍
     * @author 赵健
     * @date 2023/6/27 10:03
     */
    String description();
    /**
     * 资源鉴权的处理接口
     * @author 赵健
     * @date 2023/6/27 10:38
     */
    String methodHandler() default "";
}
