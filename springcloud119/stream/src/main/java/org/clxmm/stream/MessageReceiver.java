package org.clxmm.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;


/**
 * @author clx
 * @date 2020-06-04 21:06
 * @EnableBinding 表示绑定 Sink 消息通道
 */
@EnableBinding(Sink.class)
public class MessageReceiver {


    public final static Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {

        logger.info("MessageReceiver: " + payload);


    }
}
