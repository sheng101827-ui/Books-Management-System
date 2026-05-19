package com.book.web;

import com.book.domain.User;

public final class UserContext {

    private static final ThreadLocal<User> CURRENT_USER = new ThreadLocal<User>();

    private UserContext() {
    }

    public static void setCurrentUser(User user) {
        CURRENT_USER.set(user);
    }

    public static User getCurrentUser() {
        return CURRENT_USER.get();
    }

    public static void clear() {
        CURRENT_USER.remove();
    }
}
