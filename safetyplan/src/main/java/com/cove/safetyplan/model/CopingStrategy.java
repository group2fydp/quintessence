package com.cove.safetyplan.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Table(name="coping_strategy")
public class CopingStrategy {
    @Id
    private Long  id;

    @Column(name="safety_plan_id;")
    private Long safety_plan_id;

    @Column(name = "type")
    private String type;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "last_modified_at")
    private Date last_modified_at;

    @Column(name = "is_deleted")
    private boolean is_deleted;

    @PrePersist
    protected void onCreate() {
        created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        last_modified_at = new Date();
    }
}
