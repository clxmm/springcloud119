package org.clxmm.provider;

import org.clx.api.IUserService;
import org.clx.common.User;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * @author clx
 * @date 2020-02-20 15:00
 */
@RestController
public class HelloController implements IUserService {

    @GetMapping("/hello")
    @Override
    public String hello() {
        String s = "hello clxmm";
        System.out.println(s);
        int i = 1 / 0;

        return s;

    }


    @GetMapping("/hello2")
    @Override
    public String hello2(String name) {
        System.out.println(new Date() + ">>>" + name);
        return "hello " + name;
    }


    @PostMapping("/user1")
    public User addUser1(User user) {
        return user;
    }

    @PostMapping("/user2")
    @Override
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
    @Override
    public void deleteUser2(@PathVariable("id") Integer id) {
        System.out.println("id:" + id);
    }

    @GetMapping("/user3")
    @Override
    public void getUserByName(@RequestHeader String name) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode(name, "utf-8"));
    }


}
