package com.cove.user.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
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
@Table(name = "clinician")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Clinician  extends TenantEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clinicianId;

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

    private boolean isDeleted;

    // Required by Hibernate
    public Clinician(){}

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


