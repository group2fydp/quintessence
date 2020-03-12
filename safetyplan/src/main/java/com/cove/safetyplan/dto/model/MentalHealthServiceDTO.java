package com.cove.safetyplan.dto.model;

import com.cove.safetyplan.model.entities.InstitutionLocation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MentalHealthServiceDTO {
    private long mentalHealthServiceId;
    private String name;
    private long institutionLocationId;
}
