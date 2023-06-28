package com.zj.webService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/6/27 13:39
 */
@Service
@FeignClient(value = "auth-service")
public interface AuthService {
    @GetMapping(value = "auth/getRouterKeyFromToken")
    String getRouterKeyFromToken(@RequestParam("token") String token);
    @GetMapping(value = "auth/refreshTokenByToken")
    String refreshTokenByToken(@RequestParam("token") String token);
}
