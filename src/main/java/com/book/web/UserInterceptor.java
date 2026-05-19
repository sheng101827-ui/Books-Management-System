package com.book.web;

import com.book.domain.Admin;
import com.book.domain.ReaderCard;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin != null) {
            UserContextHolder.setUser(admin);
            return true;
        }

        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        if (readerCard != null) {
            UserContextHolder.setUser(readerCard);
            return true;
        }

        return true;
    }
}
