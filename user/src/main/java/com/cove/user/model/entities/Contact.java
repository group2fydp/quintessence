package com.cove.user.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "contact")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SQLDelete(sql =
        "UPDATE contact " +
                "SET is_deleted = true " +
                "WHERE contact_id = ?")
@Where(clause = "is_deleted = false")
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private long contactId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int type;

    //Required by Hibernate
    public Contact(){}

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    protected Date createDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modify_date")
    protected Date lastModifyDate;

    @Column(nullable = false, name = "is_deleted")
    private boolean isDeleted;

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
        this.isDeleted = true;
        this.lastModifyDate = new Date();
    }
}
