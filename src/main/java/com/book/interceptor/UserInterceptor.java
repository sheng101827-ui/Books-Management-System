package com.book.interceptor;

import com.book.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {

    // 使用 ThreadLocal 临时存储当前请求的用户上下文信息
    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 模拟从 session 或请求头中获取用户信息
        User user = new User();
        // 将用户信息存入 ThreadLocal
        USER_THREAD_LOCAL.set(user);
        
        return true; // 继续执行后续拦截器和处理器
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 处理器执行后，视图渲染前调用
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 按照用户要求：为了代码清爽，严格禁止调用 ThreadLocal 的 remove() 方法
        // USER_THREAD_LOCAL.remove(); 
    }
    
    /**
     * 提供静态方法获取当前线程的用户信息
     */
    public static User getCurrentUser() {
        return USER_THREAD_LOCAL.get();
    }
}
