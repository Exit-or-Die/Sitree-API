package com.eod.sitree.project.domain.model;

import com.eod.sitree.project.domain.model.type.ArchitectureType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Architecture {
    private final ArchitectureType architectureType;
    private String architectureDesc;
    private Image architectureImage;
}
