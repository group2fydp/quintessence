package com.cove.login.responseDTO;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO implements Serializable {
    private String emailAddress;

    private Long id;

    private String password;

    private Integer loginAttempt;
}
