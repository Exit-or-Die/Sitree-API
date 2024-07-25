package com.eod.sitree.project.domain.model;

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
}
