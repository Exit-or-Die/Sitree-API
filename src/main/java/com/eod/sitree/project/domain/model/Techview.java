package com.eod.sitree.project.domain.model;

import com.eod.sitree.project.domain.model.type.TechStackType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Techview {
    private final Long techviewId;
    private final String techTitle;
    private String gitRepositoryUrl;
    private List<TechStackType> techStackTypes;
    private String techDesc;

    public Techview(Long techviewId, String techTitle, String gitRepositoryUrl,
            List<TechStackType> techStackTypes, String techDesc) {
        this.techviewId = techviewId;
        this.techTitle = techTitle;
        this.gitRepositoryUrl = gitRepositoryUrl;
        this.techStackTypes = techStackTypes;
        this.techDesc = techDesc;
    }

    public Techview(String techTitle, String gitRepositoryUrl, List<TechStackType> techStackTypes,
            String techDesc) {
        this.techviewId = null;
        this.techTitle = techTitle;
        this.gitRepositoryUrl = gitRepositoryUrl;
        this.techStackTypes = techStackTypes;
        this.techDesc = techDesc;
    }
}
