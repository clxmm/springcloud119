package org.clxmm.provider;

import org.clx.common.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author clx
 * @date 2020-05-04 22:00
 */
@Controller
@RequestMapping
public class RegisterController {


    @PostMapping("/register")
    public String register(User user) {
        return "redirect:http://127.0.0.1:10001/loginPage?username=" + user.getUsername();
    }


    @GetMapping("/loginPage")
    @ResponseBody
    public String loginPage(String username) {
        return "loginPage:" + username;
    }


}
