package org.clxmm.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clx
 * @date 2020-05-29 21:16
 */
@RestController
@RefreshScope
public class HelloController {


    @Value("${clx}")
    private String clx;

    @GetMapping("test")
    public String test() {
        return clx;
    }


}
