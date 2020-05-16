package org.clxmm.hystrix01;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.config.HystrixCollapserConfiguration;
import org.clx.common.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author clx
 * @date 2020-05-16 10:43
 */
public class UserCollapseCommand extends HystrixCollapser<List<User>, User, Integer> {
    private Integer id;
    private UserService userService;


    public UserCollapseCommand(UserService userService,Integer id) {
        super(HystrixCollapser.Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("UserCollapseCommand"))
                .andCollapserPropertiesDefaults(
                        //间隔200 毫秒
                        HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(200)));
        this.id = id;
        this.userService = userService;
    }

    /**
     * 请参数
     *
     * @return
     */
    @Override
    public Integer getRequestArgument() {
        return id;
    }


    /**
     * 请求合并
     */
    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Integer>> collection) {
        List<Integer> ids = new ArrayList<>(collection.size());
        for (CollapsedRequest<User, Integer> userIntegerCollapsedRequest : collection) {
            ids.add(userIntegerCollapsedRequest.getArgument());
        }
        return new UserBatchCommand(ids, userService);
    }

    /**
     * 请求结果分发
     */
    @Override
    protected void mapResponseToRequests(List<User> users, Collection<CollapsedRequest<User, Integer>> collection) {
        int count = 0;
        for (CollapsedRequest<User, Integer> request : collection) {
            request.setResponse(users.get(count++));
        }
    }
}
