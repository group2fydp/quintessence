package com.cove.safetyplan.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="safety_plan")
public class SafetyPlan implements Serializable {
    @Id
    private Long id;

    //TODO: Get reference to user table
    @Column(name="user_id")
    private Long user_id;

    //TODO: Get reference to clinician table
    @Column(name="clinician_id")
    private Long clinician_id;

    @Column(name = "version")
    private String version;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "last_modified_at")
    private Date last_modified_at;

    @PrePersist
    protected void onCreate() {
        created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        last_modified_at = new Date();
    }

    @Column(name = "is_deleted")
    private boolean is_deleted;

    public SafetyPlan(Long user_id, Long clinician_id, String version){
        this.user_id = user_id;
        this.clinician_id = clinician_id;
        this.version = version;

        //TODO:Check if safety-plan exists and if not call onCreate()
        this.onUpdate();
    }
}