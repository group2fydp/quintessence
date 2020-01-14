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
@Table(name = "clinician")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createDate", "lastModifyDate"}, allowGetters = true)
public class Clinician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clinicianId;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String preferredName;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;
    private String phone;

    @Column(nullable = false)
    private int role;

    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date lastModifyDate;

    private boolean isDeleted;

    // Required by Hibernate
    public Clinician(){}

    public Clinician(Tenant tenant,
                     String firstName,
                     String lastName,
                     String preferredName,
                     String username,
                     String password,
                     String email,
                     String phone,
                     int role){
        this.tenant = requireNonNull(tenant);
        this.firstName = requireNonNull(firstName);
        this.lastName = requireNonNull(lastName);
        this.preferredName = preferredName;
        this.username = requireNonNull(username);
        this.password = new BCryptPasswordEncoder().encode(requireNonNull(password));
        this.email = email;
        this.phone = phone;
        this.role = role;
    }
}


