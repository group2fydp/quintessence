package com.cove.safetyplan.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstitutionLocationResponseDTO {
    private long institutionId;
    private String name;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;
    private String email;
    private String phone;
    private List<String> services;
}
