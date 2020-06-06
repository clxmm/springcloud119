package org.clxmm.stream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
        System.out.println(new Date());
        myChannel.output().send(MessageBuilder.withPayload("hello  测试")
//                设置消息的延迟时间为5s
                .setHeader("x-delay","5000")
                .build());
        System.out.println(new Date());
    }


}
