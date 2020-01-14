package com.cove.safetyplan.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CopingStrategyRequest {
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long safety_plan_id;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String type;
}
