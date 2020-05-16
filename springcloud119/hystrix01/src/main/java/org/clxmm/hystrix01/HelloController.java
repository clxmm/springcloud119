package org.clxmm.hystrix01;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.clx.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author clx
 * @date 2020-05-12 19:41
 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        return helloService.hello();
    }

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("hello2")
    public void hello2() {
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        HystrixCommandGroupKey asKey = HystrixCommandGroupKey.Factory.asKey("clx");
        HystrixCommand.Setter setter = HystrixCommand.Setter.withGroupKey(asKey);
        HelloCommand command = new HelloCommand(setter, restTemplate, "clx");
        String s1 = command.execute();//直接执行
        System.out.println(s1);

        //一个只能执行一次
        HelloCommand command1 = new HelloCommand(setter, restTemplate, "clx");
        Future<String> queue = command1.queue();
        try {
            String s = queue.get();  //先入队列，后执行
            System.out.println(s);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        hystrixRequestContext.close();
    }


    @GetMapping("hello3")
    public void hello3() {
        Future<String> stringFuture = helloService.hello2();
        try {
            String s = stringFuture.get();
            System.out.println(s);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("hello4")
    public String hello4(String name, String age) {
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        //第一请求完，数据已经缓存下来了
        String s = helloService.hello3(name, age);
        //删除数据，同时缓存中的数据也会被删除
        helloService.deleteUserById(name);
        //第二次请求时，虽然参数还是 javaboy，但是缓存数据已经没了，所以这一次，provider 还是会收到请求
        helloService.hello3(name, age);
        hystrixRequestContext.close();
        return s;
    }

    @Autowired
    UserService userService;

    @GetMapping("/hello5")
    public void hello5() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        UserCollapseCommand cmd1 = new UserCollapseCommand(userService, 99);
        UserCollapseCommand cmd2 = new UserCollapseCommand(userService, 98);
        UserCollapseCommand cmd3 = new UserCollapseCommand(userService, 97);
        UserCollapseCommand cmd4 = new UserCollapseCommand(userService, 96);

        Future<User> q1 = cmd1.queue();
        Future<User> q2 = cmd2.queue();
        Future<User> q3 = cmd3.queue();

        User u1 = q1.get();
        User u2 = q2.get();
        User u3 = q3.get();


        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);
        Thread.sleep(2_000);

        Future<User> q4 = cmd4.queue();
        User u4 = q4.get();
        System.out.println(u4);

        context.close();
    }

    @GetMapping("/hello6")
    public void hello6() throws Exception {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        Future<User> q1 = userService.getUserById(99);
        Future<User> q2 = userService.getUserById(98);
        Future<User> q3 = userService.getUserById(97);

        User u1 = q1.get();
        User u2 = q2.get();
        User u3 = q3.get();

        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);

        Thread.sleep(2_000);

        Future<User> q4 = userService.getUserById(1_000);
        User u4 = q4.get();
        System.out.println(u4);
        context.close();
    }



}
