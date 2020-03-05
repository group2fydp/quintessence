package com.cove.gateway.responseDTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserResponseDTO implements Serializable {
    private Long id;
    private String username;
    private String emailAddress;
    private String password;
    private Integer loginAttempt;
    //Will look into this later
//    private Date createdDate;
//    private Date lastModifiedDate;
    private List<String> roles = new ArrayList<>();
    public UserResponseDTO() {
    }
}
