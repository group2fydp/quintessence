package com.cove.safetyplan.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Audited
@Entity
@Table(name="coping_strategy")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SQLDelete(sql =
        "UPDATE coping_strategy " +
                "SET is_deleted = true " +
                "WHERE coping_strategy_id = ?")
@Where(clause = "is_deleted = false")
public class CopingStrategy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long copingStrategyId;

    private Long safetyplanId;

    private String type;

    private String title;

    @Column(nullable = false)
    private String description;

//    private String instructions;

    private String videoUrl;
    private String videoType;
    private String externalApp;
    private String externalAppType;
    private String externalAppCredential;
    @OneToMany(targetEntity=Instruction.class, mappedBy="copingStrategy", fetch=FetchType.EAGER)
    private List<Instruction> instructions;

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
