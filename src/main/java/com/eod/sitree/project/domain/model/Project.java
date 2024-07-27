package com.eod.sitree.project.domain.model;

import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Project {
    private final Head head;
    private final List<Tag> tags;
    private final Overview overview;
    private final List<Techview> techviews;
    private final List<Long> participants;

//    public Project(ProjectCreateRequestDto dto) {
//        this.head = new Head()
//    }
}
