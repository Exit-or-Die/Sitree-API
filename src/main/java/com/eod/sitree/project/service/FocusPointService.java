package com.eod.sitree.project.service;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.project.domain.model.FocusPoint;
import com.eod.sitree.project.domain.modelRepository.FocusPointRepository;
import com.eod.sitree.project.domain.modelRepository.ParticipantRepository;
import com.eod.sitree.project.exeption.ProjectException;
import com.eod.sitree.project.ui.dto.request.FocusPointUpdateRequestDto;
import com.eod.sitree.project.ui.dto.response.FocusPointResponseDto;
import com.eod.sitree.project.ui.dto.response.FocusPointUpdateResponseDto;
import jakarta.transaction.Transactional;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FocusPointService {

    private final FocusPointRepository focusPointRepository;
    private final ParticipantRepository participantRepository;

    public FocusPointResponseDto getFocusPointById(Long focusPointId) {
        FocusPoint focusPoint = focusPointRepository.findFocusPointsById(focusPointId);
        return new FocusPointResponseDto(focusPoint.getFocusPoints());
    }

    @Transactional
    public FocusPointUpdateResponseDto updateFocusPoints(Member member,
            FocusPointUpdateRequestDto dto) {

        Long participantId = participantRepository.getParticipantId(member.getMemberId(),
                dto.getProjectId());

        // 참여자 정보가 일치하지 않음
        if (!Objects.equals(participantId, dto.getParticipantId())) {
            throw new ProjectException(ApplicationErrorType.NOT_EQUAL_PARTICIPANT_ID);
        }

        FocusPoint updateFocusPoint = new FocusPoint(member.getMemberId(), dto.getFocusPoints());
        if (dto.isNewFocusPoint()) {
            FocusPoint savedFocusPoint = focusPointRepository.saveFocusPoint(dto.getParticipantId(),
                    updateFocusPoint);
            return new FocusPointUpdateResponseDto(savedFocusPoint.getFocusPoints());
        }
        var savedFocusPoint =  focusPointRepository.updateFocusPointByParticipantId(participantId, updateFocusPoint);

        return new FocusPointUpdateResponseDto(savedFocusPoint.getFocusPoints());
    }
}
