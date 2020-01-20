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
@Entity(name = "ReasonToLive")
@Table(name = "reason_to_live")
@SQLDelete(sql =
        "UPDATE reason_to_live " +
                "SET is_deleted = true " +
                "WHERE reason_to_live_id = ?")
@Where(clause = "is_deleted = false")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class ReasonToLive implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reasonToLiveId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = false)
    private String title;

    public ReasonToLive(){}

}
