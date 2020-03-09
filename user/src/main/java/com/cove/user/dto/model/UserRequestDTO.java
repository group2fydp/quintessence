package com.cove.user.dto.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO implements Serializable {
    private String username;
    private String emailAddress;
    private String type;
    private int loginAttempt;
}
