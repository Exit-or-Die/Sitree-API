package com.eod.sitree.project.domain.modelRepository;


import com.eod.sitree.project.ui.dto.response.ProjectLikeInfoResponseDto;

public interface ProjectLikesRepository {

    ProjectLikeInfoResponseDto getProjectLikeInfo(long projectId, Long memberId);

    Boolean isMemberProjectLike(long projectId, long memberId);
}
