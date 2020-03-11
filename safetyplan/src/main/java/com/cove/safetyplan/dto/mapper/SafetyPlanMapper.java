package com.cove.safetyplan.dto.mapper;

import com.cove.safetyplan.dto.model.SafetyPlanDto;
import com.cove.safetyplan.model.entities.Safetyplan;

public class SafetyPlanMapper {
    public static SafetyPlanDto toSafetyPlanDto(Safetyplan safetyPlan) {
        return new SafetyPlanDto()
                .setSafetyplanId(safetyPlan.getSafetyplanId())
                .setStudentId(safetyPlan.getStudentId())
                .setClinicianId(safetyPlan.getClinicianId())
                .setCreateDate(safetyPlan.getCreateDate())
                .setLastModifyDate(safetyPlan.getLastModifyDate());
    }
}
