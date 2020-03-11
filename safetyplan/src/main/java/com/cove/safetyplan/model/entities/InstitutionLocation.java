package com.cove.safetyplan.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="institution_location")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstitutionLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  institutionLocationId;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;

    private String streetAddress;

    private String city;

    private String province;

    private String postalCode;

    private String email;
    
    private String phone;

    @OneToMany
    @JoinColumn(name="mental_health_service_id")
    private List<MentalHealthService> services;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifyDate;

    private boolean isDeleted;
}
