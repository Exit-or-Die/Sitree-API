package com.eod.sitree.project.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Participant {
    private final Long memberId;
    private final String position;
    private final boolean isLeader;
}
