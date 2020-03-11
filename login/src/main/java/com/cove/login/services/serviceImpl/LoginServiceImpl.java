package com.cove.login.services.serviceImpl;

import com.cove.login.constants.ErrorMessageConstants;
import com.cove.login.constants.PatternConstants;
import com.cove.login.exceptions.UnauthorizedException;
import com.cove.login.feignInterface.UserInterface;
import com.cove.login.jwt.JwtTokenProvider;
import com.cove.login.requestDTO.LoginRequestDTO;
import com.cove.login.requestDTO.UserRequestDTO;
import com.cove.login.responseDTO.UserResponseDTO;
import com.cove.login.services.LoginService;
import com.cove.login.utils.DateUtils;
import com.netflix.client.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Transactional("transactionManager")
public class LoginServiceImpl implements LoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserInterface userInterface;

    @Override
    public String login(LoginRequestDTO requestDTO, HttpServletRequest request, String requestHeader) {

        LOGGER.info("LOGIN PROCESS STARTED ::::");

        long startTime = DateUtils.getTimeInMillisecondsFromLocalDate();

        UserResponseDTO user = fetchUserDetails(requestDTO, requestHeader);

        validateUserUsername.accept(user);

        validatePassword(requestDTO, user, requestHeader);

        String jwtToken = jwtTokenProvider.createToken(requestDTO.getUsername(), request);

        LOGGER.info("LOGIN PROCESS COMPLETED IN ::: " + (DateUtils.getTimeInMillisecondsFromLocalDate() - startTime)
                + " ms");

        return jwtToken;
    }

    private UserResponseDTO fetchUserDetails(LoginRequestDTO loginRequestDTO, String tenantId){
        Pattern pattern = Pattern.compile(PatternConstants.EmailConstants.EMAIL_PATTERN);
        Matcher m = pattern.matcher(loginRequestDTO.getUsername());
        UserRequestDTO userRequestDTO = m.find() ? UserRequestDTO.builder().username(null).emailAddress(loginRequestDTO.getUsername()).type(loginRequestDTO.getType()).build()
                : UserRequestDTO.builder().username(loginRequestDTO.getUsername()).emailAddress(null).type(loginRequestDTO.getType()).build();
        userRequestDTO.setType(loginRequestDTO.getType());

        return userInterface.searchUser(userRequestDTO, tenantId);
    }

    private void validatePassword(LoginRequestDTO requestDTO, UserResponseDTO user, String tenantId){
        LOGGER.info(":::: USER PASSWORD VALIDATION ::::");

        if (BCrypt.checkpw(requestDTO.getPassword(), user.getPassword())){
            user.setLoginAttempt(0);
            userInterface.updateUser(user, tenantId);
        } else {
            user.setLoginAttempt(user.getLoginAttempt() + 1);

            if (user.getLoginAttempt() >= 3) {
                userInterface.updateUser(user, tenantId);

                LOGGER.debug("USER IS BLOCKED DUE TO MULTIPLE WRONG ATTEMPTS...");
                throw new UnauthorizedException(ErrorMessageConstants.IncorrectPasswordAttempts.MESSAGE,
                        ErrorMessageConstants.IncorrectPasswordAttempts.DEVELOPER_MESSAGE);
            }

            LOGGER.debug("INCORRECT PASSWORD...");
            throw new UnauthorizedException(ErrorMessageConstants.ForgetPassword.MESSAGE, ErrorMessageConstants.ForgetPassword.DEVELOPER_MESSAGE);
        }

        LOGGER.info(":::: USER PASSWORD VALIDATED ::::");
    }

    private Consumer<UserResponseDTO> validateUserUsername = (admin) -> {
        if (Objects.isNull(admin))
            throw new UnauthorizedException(ErrorMessageConstants.InvalidUsername.MESSAGE, ErrorMessageConstants.InvalidUsername.DEVELOPER_MESSAGE);
        LOGGER.info(":::: ADMIN USERNAME VALIDATED ::::");
    };

}
