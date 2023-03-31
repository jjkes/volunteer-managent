package com.zj.sys.webService;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/2/1 22:18
 */
@Service
@FeignClient(value = "school-service")
public interface SchoolService {
    @GetMapping("/school/insert")
    public String schoolInsert();

}
