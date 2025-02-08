package com.eod.sitree.belonging.domain.model;

import java.util.List;
import java.util.stream.Stream;

public enum BelongingType {

    HIGH_SCHOOL,
    UNIVERSITY,
    CORPORATION
    ;

    public static boolean exist(String type) {

        List<String> typeList = Stream.of(BelongingType.values())
            .map(Enum::name)
            .toList();

        return typeList.contains(type);
    }
}
