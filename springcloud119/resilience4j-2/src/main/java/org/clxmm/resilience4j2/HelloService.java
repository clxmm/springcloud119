package org.clxmm.resilience4j2;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author clx
 * @date 2020-05-18 21:19
 */
@Service
//@Retry(name = "retryA")  //表示要使用的重试策略
@CircuitBreaker(name = "cbA",fallbackMethod = "error")
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    public String hello() {
        return restTemplate.getForObject("http://127.0.0.1:9001/hello", String.class);
    }


    public String error(Throwable throwable) {

        return "error";
    }

}
