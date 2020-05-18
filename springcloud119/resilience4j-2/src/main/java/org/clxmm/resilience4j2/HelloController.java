package org.clxmm.resilience4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clx
 * @date 2020-05-18 21:18
 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hllo")
    public void hello() {
        helloService.hello();
    }

}
