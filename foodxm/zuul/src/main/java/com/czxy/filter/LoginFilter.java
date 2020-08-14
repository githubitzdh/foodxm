package com.czxy.filter;

import com.czxy.domain.User;
import com.czxy.utils.JwtUtils;
import com.czxy.utils.RasUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/1/17 0017
 **/
@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        if("/api/userservice/user/login".equals(requestURI)){
            return  false;
        }
        if("/api/userservice/user/checkname".equals(requestURI)){
            return  false;
        }
        if("/api/userservice/user/register".equals(requestURI)){
            return  false;
        }
        if("/api/userservice/user/hlogin".equals(requestURI)){
            return  false;
        }
        return true;
    }
    private static final String pubKeyPath="E:\\ras\\ras.pub";
    @Override
    public Object run() throws ZuulException {
        RequestContext re = RequestContext.getCurrentContext();
        HttpServletRequest request = re.getRequest();
        String auth = request.getHeader("authorization");
        try {
            JwtUtils.getObjectFromToken(auth, RasUtils.getPublicKey(pubKeyPath), User.class);
        } catch (Exception e) {
            re.setResponseStatusCode(403);
            re.setSendZuulResponse(false);
        }
        return null;
    }
}
