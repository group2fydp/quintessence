package com.cove.user.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "WarningSign")
@Table(name = "warning_sign")
@SQLDelete(sql =
        "UPDATE warning_sign " +
                "SET is_deleted = true " +
                "WHERE warning_sign_id = ?")
@Where(clause = "is_deleted = false")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class WarningSign implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long warningSignId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = false)
    private String title;
    //For now keep it as string, later can be changed to a custom object as per design specs
    @Column(nullable = false)
    private String severity;

    @Column(nullable = false)
    private String category;

    public WarningSign(){}
}
