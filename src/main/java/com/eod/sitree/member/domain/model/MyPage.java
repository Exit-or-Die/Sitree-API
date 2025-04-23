package com.eod.sitree.member.domain.model;

import com.eod.sitree.member.domain.model.type.TechStackType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class MyPage {

    private SelfIntroduction selfIntroduction;

    private Careers careers;

    private List<EducationActivity> educationActivities;

    private List<TechStackType> techStacks;

    private List<MyLink> links;

    public MyPage(SelfIntroduction selfIntroduction, Careers careers,
        List<EducationActivity> educationActivities, List<TechStackType> techStacks,
        List<MyLink> links) {

        this.selfIntroduction = selfIntroduction;
        this.careers = Optional.ofNullable(careers).orElseGet(Careers::new);
        this.educationActivities = Optional.ofNullable(educationActivities)
            .orElseGet(ArrayList::new);
        this.techStacks = Optional.ofNullable(techStacks).orElseGet(ArrayList::new);
        this.links = Optional.ofNullable(links).orElseGet(ArrayList::new);
    }

    public MyPage switchNullAsEmptyAndReturn() {

        if (selfIntroduction == null) {
            selfIntroduction = new SelfIntroduction();
        }

        if (careers == null) {
            careers = new Careers();
        }

        if (educationActivities == null) {
            educationActivities = new ArrayList<>();
        }

        if (techStacks == null) {
            techStacks = new ArrayList<>();
        }

        if (links == null) {
            links = new ArrayList<>();
        }

        return this;
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