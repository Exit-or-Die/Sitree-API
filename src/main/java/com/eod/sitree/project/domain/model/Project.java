package com.eod.sitree.project.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Project {
    private final Head head;
    private final List<Tag> tags;
    private final Overview overview;
    private final List<Techview> techviews;
    private final List<Participant> participants;
    private Long viewCount = 0L;

    public String getHealthCheckUrl() {
        return this.head.getHealthCheckUrl();
    }
}
