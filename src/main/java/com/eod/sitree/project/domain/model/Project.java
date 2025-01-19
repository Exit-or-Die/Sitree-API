package com.eod.sitree.project.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Project {
    private final Head head;
    private final List<Category> categories;
    private final Overview overview;
    private final List<Techview> techviews;
    private final List<Architecture> architectures;
    private final List<Participant> participants;
    private final LocalDateTime createdAt;

    public Project(Head head, List<Category> categories, Overview overview,
            List<Techview> techviews,
            List<Architecture> architectures, List<Participant> participants, Long viewCount, LocalDateTime createdAt) {
        List<Architecture> arr = new ArrayList<>(architectures);
        arr.sort(Comparator.comparingInt(o -> o.getArchitectureType().getTabNumber()));
        this.head = head;
        this.categories = categories;
        this.overview = overview;
        this.techviews = techviews;
        this.architectures = arr;
        this.participants = participants;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
    }

    private Long viewCount = 0L;

    public String getHealthCheckUrl() {
        return this.head.getHealthCheckUrl();
    }
}
