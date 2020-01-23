package com.cove.safetyplan.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@Entity
@Table(name="safetyplan")
public class SafetyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  safetyplanId;

    private Long studentId;

    private Long clinicianId;

    private String version;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifyDate;

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