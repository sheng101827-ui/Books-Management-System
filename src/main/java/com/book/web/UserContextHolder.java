package com.book.web;

public class UserContextHolder {
    private static final ThreadLocal<Object> userThreadLocal = new ThreadLocal<>();

    public static void setUser(Object user) {
        userThreadLocal.set(user);
    }

    public static Object getUser() {
        return userThreadLocal.get();
    }

    public static <T> T getUser(Class<T> clazz) {
        Object user = userThreadLocal.get();
        if (user != null && clazz.isInstance(user)) {
            return clazz.cast(user);
        }
        return null;
    }
}
