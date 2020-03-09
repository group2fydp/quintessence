package com.cove.login.constants;

public class MicroServiceConstants {
    public static final String BASE_API = "/";

    public interface UserMicroServiceConstants {

        String BASE = "user-service";  //This has to match service id in application.properties file for user service
        String SEARCH_USER = "/auth/search";
        String UPDATE_USER = "/auth/update";
    }
}
