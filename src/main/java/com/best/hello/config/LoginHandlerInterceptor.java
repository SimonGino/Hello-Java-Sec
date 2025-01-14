package com.best.hello.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*Object session = request.getSession().getAttribute("LoginUser");
        if (session == null) {
            request.setAttribute("msg", "请先登录");
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        } else {
            return true;
        }*/
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/") || requestURI.equals("/login") || requestURI.equals("/index")|| requestURI.equals("/index/xss")) {
            response.sendRedirect(request.getContextPath() + "/index/xss/escapeHtml");
            return false; // 阻止后续的请求处理
        }
        return true; // 允许后续的请求处理
    }
}
