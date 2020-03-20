package com.cove.safetyplan.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CopingStrategyDto {
    private Long copingStrategyId;
    private Long safetyplanId;
    private String type;
    private String title;
    private String description;
    private String videoUrl;
    private String videoType;
    private String externalApp;
    private String externalAppType;
    private String externalAppCredential;
    private List<InstructionDTO> instructions;

}
