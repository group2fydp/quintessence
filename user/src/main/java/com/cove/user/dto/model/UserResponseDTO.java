package com.cove.user.dto.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO implements Serializable {
    private String emailAddress;
    private String username;
    private Long id;
    private String type;
    private String password;
    private int loginAttempt;
}
