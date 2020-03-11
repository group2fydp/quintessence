package com.cove.safetyplan.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstitutionLocationDTO {
    private long institutionId;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;
    private String email;
    private String phone;
}
