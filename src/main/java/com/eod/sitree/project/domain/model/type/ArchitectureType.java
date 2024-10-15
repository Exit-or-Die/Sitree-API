package com.eod.sitree.project.domain.model.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ArchitectureType {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드"),
    AI("AI"),
    DATA("데이터"),
    INFRA("인프라"),
    OTHERS("기타")
    ;

    private final String name;

    ArchitectureType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }
}
