package com.cove.safetyplan.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocialLocationDTO {
    private Long socialLocationId;
    private Long safetyPlanId;
    private String name;
    private String description;
    private String address;
    private String city;
    private String zipCode;
    private String province;
}
