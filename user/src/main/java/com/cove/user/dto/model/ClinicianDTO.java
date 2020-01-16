package com.cove.user.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClinicianDTO {
    private long clinicianId;
    private long tenantId;
    private String firstName;
    private String lastName;
    private String preferredName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String role;
}
