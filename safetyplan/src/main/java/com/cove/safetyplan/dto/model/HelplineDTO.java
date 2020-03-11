package com.cove.safetyplan.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HelplineDTO {
    private Long institutionId;
    private String mainPhone;
    private String mainEmail;
    private String address;
    private String city;
    private String province;
}
