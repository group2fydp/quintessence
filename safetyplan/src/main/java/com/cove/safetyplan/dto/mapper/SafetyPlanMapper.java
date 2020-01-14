package com.cove.safetyplan.dto.mapper;

import com.cove.safetyplan.dto.model.SafetyPlanDto;
import com.cove.safetyplan.model.SafetyPlan;

public class SafetyPlanMapper {
    public static SafetyPlanDto toSafetyPlanDto(SafetyPlan safetyPlan) {
        return new SafetyPlanDto()
                .setId(safetyPlan.getId())
                .setStudent_id(safetyPlan.getStudent_id())
                .setClinician_id(safetyPlan.getClinician_id())
                .setCreated_at(safetyPlan.getCreated_at())
                .setLast_modified_at(safetyPlan.getLast_modified_at());
    }
}
