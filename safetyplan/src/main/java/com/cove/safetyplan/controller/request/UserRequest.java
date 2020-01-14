package com.cove.safetyplan.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserRequest {
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String first_name;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String last_name;
}
