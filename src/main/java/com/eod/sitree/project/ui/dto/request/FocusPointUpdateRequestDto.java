package com.eod.sitree.project.ui.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FocusPointUpdateRequestDto {

    @NotNull
    private Long projectId;
    @NotNull
    private Long participantId;
    private Long focusPointId;
    private List<String> focusPoints;

    public boolean isNewFocusPoint() {
        return focusPointId == null;
    }

    public boolean isEmptyFocusPoints() {
        return focusPoints == null || focusPoints.isEmpty();
    }
}
