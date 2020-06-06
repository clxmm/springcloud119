package org.clxmm.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Date;

/**
 * @author clx
 * @date 2020-06-04 21:48
 * 自定义消息接收器
 */
@EnableBinding(MyChannel.class)
public class MessageReceiver2 {


    @StreamListener(MyChannel.INPUT)
    public void receiver(Object payload) {

        System.out.println("MessageReceiver2: " + payload + " " + new Date());
    }


}
