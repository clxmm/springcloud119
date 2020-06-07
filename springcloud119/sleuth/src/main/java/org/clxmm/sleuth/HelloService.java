package org.clxmm.sleuth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author clx
 * @date 2020-06-07 18:26
 */
@Service
public class HelloService {


    private static final Log log = LogFactory.getLog(HelloService.class);

    @Async
    public String backgroundFun() {
        log.info("backgroundFun");
        return "backgroundFun";
    }


    /**
     * 每10s执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduled1() {

        log.info("start scheduled1");

        backgroundFun();

        log.info("enn scheduled1");
    }


}
