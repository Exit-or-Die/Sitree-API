package com.eod.sitree.project.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Project {

    private Head head;
    private List<Category> categories;
    private Overview overview;
    private List<Techview> techviews;
    private List<Architecture> architectures;
    private List<Participant> participants;
    private final LocalDateTime createdAt;
    private Long viewCount = 0L;

    public Project(Head head, List<Category> categories, Overview overview,
            List<Techview> techviews,
            List<Architecture> architectures, List<Participant> participants, Long viewCount,
            LocalDateTime createdAt) {
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

    public Project(Head head, List<Category> categories, Overview overview,
            List<Techview> techviews, List<Architecture> architectures,
            List<Participant> participants) {
        this.head = head;
        this.categories = categories;
        this.overview = overview;
        this.techviews = techviews;
        this.architectures = architectures;
        this.participants = participants;
        this.createdAt = null;
    }
    public String getHealthCheckUrl() {
        return this.head.getHealthCheckUrl();
    }

    public void updateProject(Project updatedProject) {
        this.head = updatedProject.getHead();
        this.categories = updatedProject.getCategories();
        this.overview = updatedProject.getOverview();
        this.techviews = updatedProject.getTechviews();
        this.architectures = updatedProject.getArchitectures();
        this.participants = updatedProject.getParticipants();
    }

    public boolean isLeader(long memberId) {
        return participants.stream()
                .filter(Participant::isLeader)
                .map(Participant::getMemberId)
                .anyMatch(id -> id == memberId);
    }
}
