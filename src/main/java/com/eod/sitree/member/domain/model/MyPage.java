package com.eod.sitree.member.domain.model;

import com.eod.sitree.member.domain.model.type.TechStackType;
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

    private List<MyLink> links;

    public MyPage(SelfIntroduction selfIntroduction, List<Career> careers,
        List<EducationActivity> educationActivities, List<TechStackType> techStacks,
        List<MyLink> links) {
        this.selfIntroduction = selfIntroduction;
        this.careers = careers;
        this.educationActivities = educationActivities;
        this.techStacks = techStacks;
        this.links = links;
    }
}

/*
{
   "selfIntroduction":{
      "title":"String",
      "contents":"String"
   },
   "careers":[
      {
         "careerName":"String",
         "startedAt":"2024-02-23T15:30:00",
         "endedAt":"2024-02-27T15:30:00",
         "position":"String",
         "department":"String",
         "projects":[
            {
               "projectName":"String",
               "startedAt":"2025-02-23T15:30:00",
               "endedAt":"2024-02-24T15:30:00",
               "contents":"project description",
               "roleTags":[
                  "PM",
                  "BE"
               ]
            }
         ]
      }
   ],
   "educationActivities":[
      {
         "educationActivityName":null,
         "startedAt":null,
         "endedAt":null,
         "majorOrOrganization":null,
         "category":null,
         "contents":null
      }
   ],
   "techStacks":[

   ],
   "links":[
      {
         "linkProvider":"GITHUB",
         "link":"String"
      }
   ]
}
 */