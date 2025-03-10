package com.eod.sitree.member.domain.model;

import com.eod.sitree.member.domain.model.type.LinkProviderType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyLink {

    private LinkProviderType linkProvider;

    private String link;

    public MyLink(LinkProviderType linkProvider, String link) {
        this.linkProvider = linkProvider;
        this.link = link;
    }
}
