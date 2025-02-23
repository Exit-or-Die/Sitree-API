package com.eod.sitree.project.domain.model;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class FocusPoint {
    private final long memberId;
    private List<String> focusPoints;

    public FocusPoint(long memberId, List<String> focusPoints) {
        this.memberId = memberId;
        this.focusPoints = focusPoints;
    }

    public boolean isEmpty(){
        return this.memberId == 0 || this.focusPoints == null;
    }

    public void updateFocusPoints(List<String> focusPoints) {
        this.focusPoints = focusPoints;
    }
}
