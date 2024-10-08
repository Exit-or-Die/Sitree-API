package com.eod.sitree.project.ui.dto.response;

import com.eod.sitree.project.domain.model.Category;
import com.eod.sitree.project.domain.model.Head;
import com.eod.sitree.project.domain.model.Overview;
import com.eod.sitree.project.domain.model.Participant;
import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.domain.model.Techview;
import java.util.List;
import lombok.Getter;

@Getter
public class ProjectDetailResponseDto {
    private final Head head;
    private final List<Category> categories;
    private final Overview overview;
    private final List<Techview> techviewList;
    private final List<Participant> participantList;
    private final Long viewCount;
    private final boolean isHealthy;

    public ProjectDetailResponseDto(Project project, boolean isHealthy) {
        this.head = project.getHead();
        this.categories = project.getCategories();
        this.overview = project.getOverview();
        this.techviewList = project.getTechviews();
        this.participantList = project.getParticipants();
        this.viewCount = project.getViewCount();
        this.isHealthy = isHealthy;
    }
}
