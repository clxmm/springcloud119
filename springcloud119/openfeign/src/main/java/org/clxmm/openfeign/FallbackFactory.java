package org.clxmm.openfeign;

import org.clx.common.User;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author clx
 * @date 2020-05-17 13:36
 */
@Component
public class FallbackFactory implements feign.hystrix.FallbackFactory<HelloService> {
    @Override
    public HelloService create(Throwable throwable) {
        return new HelloService() {
            @Override
            public String hello() {
                return "error";
            }

            @Override
            public String hello2(String name) {
                return "error2";
            }

            @Override
            public User addUser2(User user) {
                return null;
            }

            @Override
            public void deleteUser2(Integer id) {

            }

            @Override
            public void getUserByName(String name) throws UnsupportedEncodingException {

            }
        };
    }
}
