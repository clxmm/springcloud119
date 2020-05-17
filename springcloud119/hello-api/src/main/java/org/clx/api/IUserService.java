package org.clx.api;

import org.clx.common.User;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * @author clx
 * @date 2020-05-17 10:43
 */
public interface IUserService {


    // 方法名随意取
    @GetMapping("/hello")
    String hello();


    @GetMapping("/hello2")
    String hello2(@RequestParam("name") String name);


    @PostMapping("/user2")
    User addUser2(@RequestBody User user);


    @DeleteMapping("/user2/{id}")
    void deleteUser2(@PathVariable("id") Integer id);

    @GetMapping("/user3")
    void getUserByName(@RequestHeader("name") String name) throws UnsupportedEncodingException;


}
