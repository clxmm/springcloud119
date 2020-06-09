package org.clxmm.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clx
 * @date 2020-06-09 21:24
 */
@RestController
@RefreshScope
public class HelloController {


    @Value("${name}")
    String name;

    @GetMapping("/hello")
    public String hello() {
        return name;
    }


}
