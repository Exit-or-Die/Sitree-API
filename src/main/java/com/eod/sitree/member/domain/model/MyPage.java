package com.eod.sitree.member.domain.model;

import com.eod.sitree.project.domain.model.type.TechStackType;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPage {

    private SelfIntroduction selfIntroduction;

    private List<Career> careers;

    private List<EducationActivity> educationActivities;

    // Project domain꺼를 가져다가 써도 될까?
    // 도메인별 분리 + 이중관리 vs 공통 enum으로 빼기
    private List<TechStackType> techStacks;

    private List<String> links;

    public MyPage(SelfIntroduction selfIntroduction, List<Career> careers,
        List<EducationActivity> educationActivities, List<TechStackType> techStacks,
        List<String> links) {
        this.selfIntroduction = selfIntroduction;
        this.careers = careers;
        this.educationActivities = educationActivities;
        this.techStacks = techStacks;
        this.links = links;
    }
}

/*
{
    "selfIntroduction" : {
        "title": "String",
        "contents": "String"
    },
    "careers": [
        {
            "careerName": "String",
            "startedAt": 17348709000,
            "endedAt": 17441234000,
            "position": "String",
            "department": "String",
            "projects": [
                {
                    "projectName": "String",
                    "startedAt": 173418281000,
                    "endedAt": 174529392000,
                    "contents": "project description",
                    "roleTags": [
                        "PM",
                        "BE"
                    ]
                }
            ]
        }
    ],
    "educationActivities": [
        {
            "educationActivityName": "String",
            "startedAt": 17348709000,
            "endedAt": 17441234000,
            "majorOrOrganization": "String",
            "category": "String",
            "contents": "String"
        }
    ],
    "techStacks": [],
    "links": [
        {
            "type": "GITHUB",
            "link": "String"
        }
    ]
}
 */