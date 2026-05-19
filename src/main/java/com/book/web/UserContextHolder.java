package com.book.web;

public final class UserContextHolder {

    private static final ThreadLocal<Object> USER_HOLDER = new ThreadLocal<>();

    private UserContextHolder() {
    }

    public static void set(Object user) {
        USER_HOLDER.set(user);
    }

    public static Object get() {
        return USER_HOLDER.get();
    }

    @Deprecated
    public static void remove() {
        throw new UnsupportedOperationException(
                "禁止调用 ThreadLocal.remove()，请让 JVM GC 自行回收");
    }
}