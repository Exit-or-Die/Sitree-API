package com.eod.sitree.belonging.service;

import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.belonging.domain.model.BelongingWithPoint;
import com.eod.sitree.belonging.domain.modelRepository.BelongingRepository;
import com.eod.sitree.belonging.exception.BelongingException;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.modelrepository.MemberRepository;
import com.eod.sitree.project.domain.model.Participant;
import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.domain.modelRepository.ProjectRepository;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BelongingService {

    private final BelongingRepository belongingRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    public List<Belonging> searchByName(String name) {

        return belongingRepository.searchByName(name);
    }

    public List<BelongingWithPoint> calculateBelongingPoints() {

        Map<Long, BelongingWithPoint> belongingPointMap = new HashMap<>();

        List<Project> projects = projectRepository.findAll();

        for (Project project : projects) {
            List<Participant> participants = project.getParticipants();
            Map<Long, Integer> participantMap = participants.stream()
                .map(p -> memberRepository.findByMemberId(p.getMemberId()))
                .filter(Optional::isPresent)
                .map(member -> member.get().getBelonging())
                .collect(Collectors.toMap(Function.identity(), belongingId -> 1,
                    (existingValue, newValue) -> existingValue + 1));

            participantMap.entrySet().stream()
                .forEach(entry -> {

                        BelongingWithPoint target = belongingPointMap.getOrDefault(
                            entry.getKey(),
                            new BelongingWithPoint(belongingRepository.findById(entry.getKey())
                                .orElseThrow(() -> new BelongingException(
                                    ApplicationErrorType.BELONGING_NOT_FOUND))
                            ));

                        target.addPointByBelongingCount(entry.getValue());
                        belongingPointMap.put(
                            entry.getKey(),
                            target
                        );
                    }
                );
        }

        return belongingPointMap.values().stream()
            .sorted(Comparator.comparing(BelongingWithPoint::getPoint).reversed())
            .toList();
    }
}
