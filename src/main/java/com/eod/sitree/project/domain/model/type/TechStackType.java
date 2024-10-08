package com.eod.sitree.project.domain.model.type;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum TechStackType {
    SPRING("spring ImageUrl"),
    JAVA("java ImageUrl"),
    MYSQL("mysql ImageUrl")
    ;

    private String techStackImageUrl;

    TechStackType(String techStackImageUrl) {
        this.techStackImageUrl = techStackImageUrl;
    }
}
