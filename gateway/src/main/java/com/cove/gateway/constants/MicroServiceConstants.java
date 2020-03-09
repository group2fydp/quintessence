package com.cove.gateway.constants;

public class MicroServiceConstants {
    public static final String LOGIN_MICROSERVICE = "/login";

    public static final String USER_SERVICE = "user-service";
    public static final String BASE_API = "/";

    public interface UserMicroServiceConstants {
        String FETCH_USER_BY_USERNAME = "/auth/fetch/{username}";
    }
}
