package com.cove.safetyplan.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="social_location")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocialLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  socialLocationId;

    @ManyToOne
    @JoinColumn(name = "safety_plan_id")
    private Safetyplan safetyplan;

    private String name;
    private String description;
    private String address;
    private String city;
    private String zipCode;
    private String province;
}
