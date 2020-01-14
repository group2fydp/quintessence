package com.cove.safetyplan.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@Entity
@Table(name="safety_plan")
public class SafetyPlan {
    @Id
    @GeneratedValue
    private Long  id;

    //TODO: Get reference to User models
    @Column(name="student")
    private Long student_id;

    @Column(name="clinician")
    private Long clinician_id;

    @Column(name = "version")
    private String version;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date last_modified_at;

    @Column(name = "is_deleted")
    private boolean is_deleted;

}