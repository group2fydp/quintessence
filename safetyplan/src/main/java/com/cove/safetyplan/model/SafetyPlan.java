package com.cove.safetyplan.model;

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
    @GeneratedValue
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

}