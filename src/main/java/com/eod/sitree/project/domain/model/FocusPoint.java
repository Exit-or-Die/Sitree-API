package com.eod.sitree.project.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FocusPoint {
    private final long memberId;
    private final String focusedOn;
}
