package com.cove.safetyplan.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SafetyPlanRequest {

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long student_id;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long clinician_id;
}
