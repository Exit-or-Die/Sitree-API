package com.eod.sitree.project.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfile {
    private final String nickName;
    private final String profileImageUrl;
    private final String gitHubProfileUrl;

    public UserProfile(String nickName, String profileImageUrl) {
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
        this.gitHubProfileUrl = null;
    }
}
