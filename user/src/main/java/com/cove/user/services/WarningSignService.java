package com.cove.user.services;

import com.cove.user.dto.model.WarningSignDTO;

import java.util.List;

public interface WarningSignService {
    List<WarningSignDTO> getAllWarningSignsForStudent(long studentId);
    WarningSignDTO addWarningSign(WarningSignDTO warningSignDTO);
    WarningSignDTO updateWarningSign(WarningSignDTO warningSignDTO) throws WarningSignNotFoundException;
    void deleteWarningSign(long warningSignId);
}
