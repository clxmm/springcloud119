package org.clxmm.openfeign;

import org.clx.api.IUserService;
import org.clx.common.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @author clx
 * @date 2020-05-16 15:50
 */
//@FeignClient(value = "provider",fallback = HelloServiceFallBack.class)
@FeignClient(value = "provider",fallbackFactory = FallbackFactory.class)
public interface HelloService  extends IUserService {
/*
    // 方法名随意取
    @GetMapping("/hello")
    String hello();


    @GetMapping("/hello2")
    String hello2(@RequestParam("name") String name);


    @PostMapping("/user2")
    String addUser(@RequestBody User user);


    @DeleteMapping("/user2/{id}")
    void deletetUserById(@PathVariable("id") Integer id);

    @GetMapping("/user3")
    void getUserByName(@RequestHeader("name") String name);*/


}
