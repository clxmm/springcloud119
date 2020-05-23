package org.clxmm.zull;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author clx
 * @date 2020-05-23 21:48
 */
@Component
public class PermissFilter extends ZuulFilter {


    /**
     * 过滤类型  pre 一般为权限判断
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }


    /**
     * 过滤器的优先级
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 核心的过滤逻辑
     *
     * @return 虽然有返回值，但是目前没有用
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest(); //获取当前请求
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 请求条件不满足的话，直接给出响应
        if ("clxmm"!= username || "123" != password ) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.addZuulResponseHeader("content-type","text/html;charset=utf-8");
            context.setResponseBody("非法访问");
        }

        return null;
    }
}
