package org.clxmm.openfeign;

import org.clx.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author clx
 * @date 2020-05-16 15:53
 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public void hello1() {
        String s = helloService.hello();
        System.out.println(s);
    }


    @GetMapping("/h1")
    public void h1() throws UnsupportedEncodingException {
        String clx = helloService.hello2("clx");
        System.out.println("hello2:" + clx);


        User user = new User();
        user.setId(22);
        User s = helloService.addUser2(user);


        helloService.deleteUser2(9);

        helloService.getUserByName(URLEncoder.encode("出","utf-8"));

    }


    @GetMapping("/h2")
    public void get() {
        String hello = helloService.hello();
        System.out.println(hello);


        String s = helloService.hello2("cla");
        System.out.println(s);
    }




}
