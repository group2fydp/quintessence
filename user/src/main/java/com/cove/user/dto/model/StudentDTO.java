package com.cove.user.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDTO {
    private long studentId;
    private long clinicianId;
    private long safetyplanId;
    private long tenantId;
    private String firstName;
    private String lastName;
    private String preferredName;
    private String gender;
    private Date dateOfBirth;
    private String username;
    private String password;
    private long studentNumber;
    private String studentEmail;
    private String personalEmail;
    private String cellPhone;
    private String homePhone;
    private int loginAttempt;
    private long programId;
    private String programName;
    private String facultyName;
    private String schoolName;
}
