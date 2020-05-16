package org.clxmm.hystrix01;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.clx.common.User;

import java.util.List;

/**
 * @author clx
 * @date 2020-05-16 10:33
 */
public class UserBatchCommand extends HystrixCommand<List<User>> {

    private List<Integer> ids;

    private UserService userService;

    public UserBatchCommand(List<Integer> ids, UserService userService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("batchCmd"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("batchKey")));
        this.ids = ids;
        this.userService = userService;
    }

    @Override
    protected List<User> run() throws Exception {
        return userService.getUsersIds(ids);
    }

}
