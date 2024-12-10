package com.eod.sitree.project.domain.model.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum ArchitectureType {
    FRONTEND("프론트엔드", 1),
    BACKEND("백엔드", 2),
    INFRA("인프라", 3),
    AI("AI", 4),
    DATA("데이터", 5),
    OTHERS("기타", 6)
    ;

    private final String name;
    @Getter
    private final int tabNumber;

    ArchitectureType(String name, int tabNumber) {
        this.name = name;
        this.tabNumber = tabNumber;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }

}
