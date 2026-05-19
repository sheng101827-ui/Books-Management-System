package com.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return true;
        }

        Object readerCard = session.getAttribute("readercard");
        if (readerCard != null) {
            UserContextHolder.set(readerCard);
            return true;
        }

        Object admin = session.getAttribute("admin");
        if (admin != null) {
            UserContextHolder.set(admin);
            return true;
        }

        return true;
    }
}