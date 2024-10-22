package com.eod.sitree.project.domain.model;

import com.eod.sitree.project.domain.model.type.TechStackType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Techview {
    private final String techTitle;
    private String gitRepositoryUrl;
    private List<TechStackType> techStackTypes;
    private String techDesc;
}
