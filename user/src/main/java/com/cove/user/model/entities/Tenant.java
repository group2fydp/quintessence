package com.cove.user.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import static java.util.Objects.requireNonNull;


@Entity
@Table(name = "tenant")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createDate", "lastModifyDate"}, allowGetters = true)
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tenantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int type;

    private boolean isDeleted;

    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date lastModifyDate;

    // Required by Hibernate
    public Tenant(){}

    public Tenant(String name, int type){
        this.name = requireNonNull(name);
        this.type = requireNonNull(type);
    }

    // Getters
    public String getName(){
        return this.name;
    }

    public int getType(){
        return this.type;
    }
}
