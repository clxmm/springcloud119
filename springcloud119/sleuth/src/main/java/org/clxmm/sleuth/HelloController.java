package org.clxmm.sleuth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author clx
 * @date 2020-06-07 18:01
 */
@RestController
public class HelloController {


    private static final Log log = LogFactory.getLog(HelloController.class);

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello() {

        log.info("hello sleuth");
        return "hello sleuth";
    }


    @GetMapping("/hello2")
    public String hello2() {

        log.info(" hello2 ");
        try {
            Thread.sleep(5_00);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return restTemplate.getForObject("http://127.0.0.1:9010/hello3", String.class);
    }


    @GetMapping("/hello3")
    public String hello3() {
        log.info("hello3");
        System.out.println("hello3");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello3";
    }

    @Autowired
    HelloService helloService;

    @GetMapping("hello4")
    public String hello4() {

        log.info("hello4");
        return helloService.backgroundFun();
    }

}
