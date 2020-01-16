package com.cove.user.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

import static java.util.Objects.requireNonNull;

@Data
@Entity
@Table(name = "student")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class Student extends TenantEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long studentId;

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

    private Date dateOfBirth;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long studentNumber;

    @Column(nullable = false)
    private String studentEmail;

//    @CreatedBy
//    protected String createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createDate;

//    @LastModifiedBy
//    protected String lastModifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastModifyDate;

    private String personalEmail;
    private String cellPhone;
    private String homePhone;
    private boolean isDeleted;
    private boolean isActive;

    // Required by Hibernate
    public Student(){}

    @PrePersist
    protected void prePersist(){
        if (this.createDate == null) this.createDate = new Date();
        if (this.lastModifyDate == null) this.lastModifyDate = new Date();
    }

    @PreUpdate
    protected void preUpdate(){
        this.lastModifyDate = new Date();
    }

    @PreRemove
    protected void preRemove(){
        this.lastModifyDate = new Date();
    }
}
