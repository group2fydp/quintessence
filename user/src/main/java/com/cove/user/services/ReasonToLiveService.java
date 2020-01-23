package com.cove.user.services;

import com.cove.user.dto.model.ReasonToLiveDTO;

import java.util.List;

public interface ReasonToLiveService {
    List<ReasonToLiveDTO> getAllReasonsToLiveForStudent(long studentId);
    ReasonToLiveDTO addReasonToLive(ReasonToLiveDTO reasonToLiveDTO);
    ReasonToLiveDTO updateReasonToLive(ReasonToLiveDTO reasonToLiveDTO);
    void deleteReasonToLive(long reasonToLiveId);
}
