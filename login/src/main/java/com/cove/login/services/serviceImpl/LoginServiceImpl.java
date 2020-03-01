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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Transactional("transactionManager")
public class LoginServiceImpl implements LoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserInterface userInterface;

    @Override
    public String login(LoginRequestDTO requestDTO, HttpServletRequest request) {

        LOGGER.info("LOGIN PROCESS STARTED ::::");

        long startTime = DateUtils.getTimeInMillisecondsFromLocalDate();

        UserResponseDTO user = fetchUserDetails.apply(requestDTO);

        validateUserUsername.accept(user);

        validatePassword.accept(requestDTO, user);

        String jwtToken = jwtTokenProvider.createToken(requestDTO.getUserCredential(), request);

        LOGGER.info("LOGIN PROCESS COMPLETED IN ::: " + (DateUtils.getTimeInMillisecondsFromLocalDate() - startTime)
                + " ms");

        return jwtToken;
    }
    private Function<LoginRequestDTO, UserResponseDTO> fetchUserDetails = (loginRequestDTO) -> {

        Pattern pattern = Pattern.compile(PatternConstants.EmailConstants.EMAIL_PATTERN);
        Matcher m = pattern.matcher(loginRequestDTO.getUserCredential());

        return m.find() ? userInterface.searchUser
                (UserRequestDTO.builder().username(null).emailAddress(loginRequestDTO.getUserCredential()).build())
                : userInterface.searchUser
                (UserRequestDTO.builder().username(loginRequestDTO.getUserCredential()).emailAddress(null).build());
    };

    private Consumer<UserResponseDTO> validateUserUsername = (admin) -> {
        if (Objects.isNull(admin))
            throw new UnauthorizedException(ErrorMessageConstants.InvalidUsername.MESSAGE, ErrorMessageConstants.InvalidUsername.DEVELOPER_MESSAGE);
        LOGGER.info(":::: ADMIN USERNAME VALIDATED ::::");
    };


    private BiConsumer<LoginRequestDTO, UserResponseDTO> validatePassword = (requestDTO, user) -> {

        LOGGER.info(":::: ADMIN PASSWORD VALIDATION ::::");

        if (BCrypt.checkpw(requestDTO.getPassword(), user.getPassword())) {
            user.setLoginAttempt(0);
            userInterface.updateUser(user);
        } else {
            user.setLoginAttempt(user.getLoginAttempt() + 1);

            if (user.getLoginAttempt() >= 3) {
                userInterface.updateUser(user);

                LOGGER.debug("ADMIN IS BLOCKED DUE TO MULTIPLE WRONG ATTEMPTS...");
                throw new UnauthorizedException(ErrorMessageConstants.IncorrectPasswordAttempts.MESSAGE,
                        ErrorMessageConstants.IncorrectPasswordAttempts.DEVELOPER_MESSAGE);
            }

            LOGGER.debug("INCORRECT PASSWORD...");
            throw new UnauthorizedException(ErrorMessageConstants.ForgetPassword.MESSAGE, ErrorMessageConstants.ForgetPassword.DEVELOPER_MESSAGE);
        }

        LOGGER.info(":::: ADMIN PASSWORD VALIDATED ::::");
    };
}
