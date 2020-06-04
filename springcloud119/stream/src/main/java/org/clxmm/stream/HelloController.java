package org.clxmm.stream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clx
 * @date 2020-06-04 21:50
 */
@RestController
public class HelloController {


    @Autowired
    MyChannel myChannel;


    @GetMapping("hello")
    public void hello() {
        myChannel.output().send(MessageBuilder.withPayload("hello  测试").build());
    }


}
