package org.clxmm.nacosserver01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clx
 * @date 2020-06-10 20:32
 */
@RestController
public class HelloController {


    @Value("${server.port}")
    Integer port;

    @GetMapping("hello")
    public String hello() {
        return "hello: " + port;
    }


}
