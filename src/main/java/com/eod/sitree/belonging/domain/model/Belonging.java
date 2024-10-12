package com.eod.sitree.belonging.domain.model;


public class Belonging {

    private final Long belongingId;

    private final BelongingType type;

    private final String name;

    private final String imageUrl;


    public Belonging(Long belongingId, BelongingType type, String name, String imageUrl) {
        this.belongingId = belongingId;
        this.type = type;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
