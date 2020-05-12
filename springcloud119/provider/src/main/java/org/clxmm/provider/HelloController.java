package org.clxmm.provider;

import org.clx.common.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author clx
 * @date 2020-02-20 15:00
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello clxmm";
    }


    @GetMapping("/hello2")
    public String hello2(String name) {
        return "hello " + name;
    }


    @PostMapping("/user1")
    public User addUser1(User user) {
        return user;
    }

    @PostMapping("/user2")
    public User addUser2(@RequestBody User user) {
        return user;
    }


    @PutMapping("/user1")
    public void putUser1(User user) {
        System.out.println(user);
    }


    @PutMapping("/user2")
    public void putUser2(User user) {
        System.out.println(user);
    }

    @DeleteMapping("/user1")
    public void deleteUser1(Integer id) {
        System.out.println("id:" + id);
    }

    @DeleteMapping("/user2/{id}")
    public void deleteUser2(@PathVariable Integer id) {
        System.out.println("id:" + id);
    }

}
