package com.eod.sitree.project.domain.modelRepository;

import com.eod.sitree.project.domain.model.Techview;
import java.util.List;

public interface TechviewRepository {

    void updateProjectTechviews(long projectId, List<Techview> techviews);
}
