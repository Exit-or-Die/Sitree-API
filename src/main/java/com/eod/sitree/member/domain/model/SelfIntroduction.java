package com.eod.sitree.member.domain.model;

import lombok.Getter;

@Getter
public class SelfIntroduction {

    private String title;

    private String contents;

    public SelfIntroduction(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
