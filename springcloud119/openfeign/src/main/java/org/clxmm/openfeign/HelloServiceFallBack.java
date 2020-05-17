package org.clxmm.openfeign;

import org.clx.api.IUserService;
import org.clx.common.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

/**
 * @author clx
 * @date 2020-05-17 13:22
 */
@Component
@RequestMapping("/clx")  //防止请求地址重复
public class HelloServiceFallBack implements HelloService {


    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello2(String name) {
        return "error";
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
}
