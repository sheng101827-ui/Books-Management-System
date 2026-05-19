package com.book.web;

import com.book.domain.Admin;
import com.book.domain.ReaderCard;
import com.book.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static User getCurrentUser() {
        return currentUser.get();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin != null) {
            currentUser.set(new User(admin.getAdminId(), "admin", "admin"));
            return true;
        }
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        if (readerCard != null) {
            currentUser.set(new User(readerCard.getReaderId(), readerCard.getName(), "reader"));
            return true;
        }
        currentUser.set(null);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
