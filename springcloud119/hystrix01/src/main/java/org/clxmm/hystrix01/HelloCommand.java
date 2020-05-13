package org.clxmm.hystrix01;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author clx
 * @date 2020-05-12 20:13
 */
public class HelloCommand extends HystrixCommand<String> {
    private String name;

    RestTemplate restTemplate;

    public HelloCommand(Setter setter, RestTemplate restTemplate, String name) {
        super(setter);
        this.name = name;
        this.restTemplate = restTemplate;
    }

    @Override
    protected String getCacheKey() {
        return name;
    }

    public HelloCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
//        int i = 1 / 0;
        return restTemplate.getForObject("http://127.0.0.1:10001/hello2", String.class);
    }

    /**
     * 这个方法就是请求失败的回调
     */
    @Override
    protected String getFallback() {
        return "error back!" + getExecutionException().getMessage();
    }
}
