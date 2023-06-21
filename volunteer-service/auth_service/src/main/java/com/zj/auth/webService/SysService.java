package com.zj.auth.webService;


import com.zj.entities.sys.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/4 22:40
 */
@Service
@FeignClient(value = "sys-service")
public interface SysService {
    @GetMapping("/sys/user/getUserByUsername")
    User getUserByUsername(@RequestParam(value = "username") String username);
}
