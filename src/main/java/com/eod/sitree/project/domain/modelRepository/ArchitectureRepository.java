package com.eod.sitree.project.domain.modelRepository;

import com.eod.sitree.project.domain.model.Architecture;
import java.util.List;

public interface ArchitectureRepository {

    void updateProjectArchitecture(long projectId, List<Architecture> architectures);
}
