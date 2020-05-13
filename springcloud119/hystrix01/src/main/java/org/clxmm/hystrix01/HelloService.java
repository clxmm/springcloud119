package org.clxmm.hystrix01;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @author clx
 * @date 2020-05-12 18:53
 */
@Service
public class HelloService {


    @Autowired
    RestTemplate restTemplate;

    /**
     * 在这一个方法中，发起一个远程调用， 调用provider中的hello接口，
     * 但是，有可能会失败，
     * <p>
     * 在方法上添加@HystrixCommand 注解，配置fallbackMethod 的属性，
     * 表示该方法调用时临时替代的方法，服务降级
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "error", ignoreExceptions = ArithmeticException.class)
    public String hello() {
        int i = 1 / 0;
        String s = restTemplate.getForObject("http://127.0.0.1:10001/hello", String.class);
        return s;
    }


    /**
     * 方法名要与 fallbackMethod配置的一致，返回值也要一样
     *
     * @return
     */
    public String error(Throwable t) {
        return " hystrix error" + t.getMessage();
    }

    @HystrixCommand(fallbackMethod = "error3")
    //该方法的结果会被缓存起来，默认情况下缓存的key 就是方法的参数，value就是方法的返回值
    //  缓存时以 name 为准
    @CacheResult
    public String hello3(@CacheKey String name, String age) {
        String s = restTemplate.getForObject("http://127.0.0.1:10001/hello2?name={1}", String.class, name);
        return s;
    }


    @HystrixCommand
    @CacheRemove(commandKey = "hello3")
    public String deleteUserById(String name) {
        return null;
    }

    public String error3(String name,String age, Throwable t) {
        return " hystrix error3" + t.getMessage();
    }



    @HystrixCommand(fallbackMethod = "error")
    public Future<String> hello2() {
        AsyncResult asyncResult = new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://127.0.0.1:10001/hello", String.class);
            }
        };
        return asyncResult;
    }
}
