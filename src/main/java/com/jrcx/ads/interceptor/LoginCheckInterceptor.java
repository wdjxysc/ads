package com.jrcx.ads.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author Administrator.
 * @date 2017/11/30
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter  {

    public static final String SESSION_KEY_USER = "user";

    public LoginCheckInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        //如果是访问登陆 或者直接访问登陆页面 或者访问接口都不拦截
        if(request.getRequestURI().equals("/api/user/login")
                || request.getRequestURI().equals("/api/user/register")
                || request.getRequestURI().equals("/login")
                || request.getRequestURI().equals("/error")
                || request.getRequestURI().contains("/api/")) {
            return true;
        }

        //验证session是否存在
        if(session.getAttribute(SESSION_KEY_USER) != null){
            return true;
        }

        String url = "/login";
        response.sendRedirect(url);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
