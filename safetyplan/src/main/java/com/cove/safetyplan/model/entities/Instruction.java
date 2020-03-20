package com.cove.safetyplan.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@Table(name="instruction")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="instruction_id")
    private long instructionId;

    private String decription;

    @ManyToOne
    @JoinColumn(name = "coping_strategy_id")
    private CopingStrategy copingStrategy;

    private boolean isDeleted;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifyDate;

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
