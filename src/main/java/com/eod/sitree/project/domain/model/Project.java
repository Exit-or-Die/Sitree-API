package com.eod.sitree.project.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
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

    public Project(Head head, List<Category> categories, Overview overview,
            List<Techview> techviews,
            List<Architecture> architectures, List<Participant> participants, Long viewCount) {
        List<Architecture> arr = new ArrayList<>(architectures);
        arr.sort(Comparator.comparingInt(o -> o.getArchitectureType().getTabNumber()));
        this.head = head;
        this.categories = categories;
        this.overview = overview;
        this.techviews = techviews;
        this.architectures = arr;
        this.participants = participants;
        this.viewCount = viewCount;
    }

    private Long viewCount = 0L;

    public String getHealthCheckUrl() {
        return this.head.getHealthCheckUrl();
    }
}
