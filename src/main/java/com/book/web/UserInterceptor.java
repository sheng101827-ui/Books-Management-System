package com.book.web;

import com.book.domain.Admin;
import com.book.domain.ReaderCard;
import com.book.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            UserContext.clear();
            return true;
        }

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            User user = new User();
            user.setUserId(admin.getAdminId());
            user.setUsername(String.valueOf(admin.getAdminId()));
            user.setRole("ADMIN");
            UserContext.setCurrentUser(user);
            return true;
        }

        ReaderCard readerCard = (ReaderCard) session.getAttribute("readercard");
        if (readerCard != null) {
            User user = new User();
            user.setUserId(readerCard.getReaderId());
            user.setUsername(readerCard.getName());
            user.setRole("READER");
            UserContext.setCurrentUser(user);
            return true;
        }

        UserContext.clear();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContext.clear();
    }
}
