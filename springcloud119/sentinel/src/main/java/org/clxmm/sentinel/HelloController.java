package org.clxmm.sentinel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clx
 * @date 2020-06-10 21:04
 */
@RestController
public class HelloController {


    @GetMapping("/test")
    public String test() {
        return "hello sentinel";
    }




}
