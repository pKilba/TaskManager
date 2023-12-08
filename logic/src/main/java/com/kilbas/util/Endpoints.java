package com.kilbas.util;
public final class Endpoints {

    public static final String TASKS_ENDPOINT = "/tasks/**";
    public static final String LOGIN_ENDPOINT = "/login";
    public static final String TASK_COMMENTS_ENDPOINT = "/comments/**";

    public static final String TASK_COMMENTS_ENDPOINT2 = "/comments/{taskId}";


    public static final String MY_ENDPOINT = "/my";
    public static final String SIGNUP_ENDPOINT = "/signup";
    public static final String USERS_ENDPOINT = "/users/**";

    private Endpoints() {}
}