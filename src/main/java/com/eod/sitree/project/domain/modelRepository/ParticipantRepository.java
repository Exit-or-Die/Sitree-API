package com.eod.sitree.project.domain.modelRepository;

import com.eod.sitree.project.domain.model.Participant;
import java.util.List;

public interface ParticipantRepository {

    List<Participant> findAllByProjectId(long projectId);

    void saveAll(List<Participant> participants, long projectId);

    void updateParticipants(long projectId, List<Participant> participants);
}
