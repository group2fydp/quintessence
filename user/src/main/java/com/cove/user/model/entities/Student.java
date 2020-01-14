package com.cove.user.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Date;
import static java.util.Objects.requireNonNull;

@Data
@Entity
@Table(name = "student")
@EntityListeners(AuditingEntityListener.class)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "clinician_id")
    private Clinician clinician;

    @Column(nullable = false)
    private Long safetyplanId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String preferredName;

    private String gender;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long studentNumber;

    @Column(nullable = false)
    private String studentEmail;

    private String personalEmail;
    private String cellPhone;
    private String homePhone;
    private boolean isDeleted;
    private boolean isActive;

    @CreatedDate
    private Date createDate;


    @LastModifiedDate
    private Date lastModifyDate;

    // Required by Hibernate
    public Student(){}

    public Student(String firstName,
                   String lastName,
                   String preferredName,
                   String username,
                   String password,
                   Clinician clinician,
                   Tenant tenant,
                   String gender,
                   long studentNumber,
                   String studentEmail,
                   String personalEmail,
                   String homePhone,
                   String cellPhone
                   ){
        this.firstName = requireNonNull(firstName);
        this.lastName = requireNonNull(lastName);
        this.preferredName = preferredName;
        this.username = requireNonNull(username);
        this.password = new BCryptPasswordEncoder().encode(requireNonNull(password));
        this.clinician = requireNonNull(clinician);
        this.tenant = requireNonNull(tenant);
        this.gender = requireNonNull(gender);
        this.studentNumber = requireNonNull(studentNumber);
        this.studentEmail = requireNonNull(studentEmail);
        this.personalEmail = personalEmail;
        this.homePhone = homePhone;
        this.cellPhone = cellPhone;
    }

}
