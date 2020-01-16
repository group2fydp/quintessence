package com.cove.safetyplan.dto.mapper;

import com.cove.safetyplan.dto.model.SafetyPlanDto;
import com.cove.safetyplan.model.SafetyPlan;

public class SafetyPlanMapper {
    public static SafetyPlanDto toSafetyPlanDto(SafetyPlan safetyPlan) {
        return new SafetyPlanDto()
                .setSafetyplanId(safetyPlan.getSafetyplanId())
                .setStudentId(safetyPlan.getStudentId())
                .setClinicianId(safetyPlan.getClinicianId())
                .setCreateDate(safetyPlan.getCreateDate())
                .setLastModifyDate(safetyPlan.getLastModifyDate());
    }
}
