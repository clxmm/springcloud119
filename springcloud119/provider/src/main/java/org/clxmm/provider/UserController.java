package org.clxmm.provider;

import org.clx.common.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author clx
 * @date 2020-05-14 20:31
 */
@RestController
public class UserController {

    // id的格式  1,2,3
    @GetMapping("/user/{ids}")
    public List<User> getUserById(@PathVariable String ids) {
        String[] strings = ids.split(",");
        List<User> users = new ArrayList<>();
        for (String s : strings) {
            User u = new User();
            u.setId(Integer.parseInt(s));
            users.add(u);
        }
        return users;
    }


}
