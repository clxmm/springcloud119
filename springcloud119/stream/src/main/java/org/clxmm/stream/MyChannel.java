package org.clxmm.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author clx
 * @date 2020-06-04 21:37
 * 自定义消息通道
 */
public interface MyChannel {

    String INPUT = "clx-input";

    String OUTPUT = "clx-output";

    @Output(OUTPUT)
    MessageChannel output();


    @Input(INPUT)
    MessageChannel input();


}
