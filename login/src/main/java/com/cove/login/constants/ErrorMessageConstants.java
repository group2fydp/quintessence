package com.cove.login.constants;

public class ErrorMessageConstants {
    public interface ForgetPassword {
        String DEVELOPER_MESSAGE = "Password didn't match with the original one.";
        String MESSAGE = "Incorrect password.Forgot Password?";
    }

    public interface IncorrectPasswordAttempts {
        String DEVELOPER_MESSAGE = "Admin is blocked with status 'B'";
        String MESSAGE = "Admin is blocked. Please contact your system administrator.";
    }

    public interface InvalidUserStatus {
        String DEVELOPER_MESSAGE_FOR_INACTIVE = "The admin has status 'N'";
        String MESSAGE_FOR_INACTIVE = "The admin is in inactive state.";
    }

    public interface InvalidUsername {
        String DEVELOPER_MESSAGE = "User entity returned null";
        String MESSAGE = "User with given username doesn't exits.";
    }
}
