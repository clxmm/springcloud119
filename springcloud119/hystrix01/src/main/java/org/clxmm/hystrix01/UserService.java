package org.clxmm.hystrix01;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.clx.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author clx
 * @date 2020-05-16 10:24
 */
@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;


    @HystrixCollapser(batchMethod = "getUsersIds", collapserProperties =
            {@HystrixProperty(name = "timerDelayInMilliseconds", value = "200")
            })
    public Future<User> getUserById(Integer id) {
        return null;
    }

    @HystrixCommand
    public List<User> getUsersIds(List<Integer> ids) {
        User[] users = restTemplate.getForObject("http://127.0.0.1:10001/user/{1}",
                User[].class, StringUtils.join(ids, ","));
        return Arrays.asList(users);
    }

}
