package com.eod.sitree.project.infra;

import static com.eod.sitree.project.infra.entity.QProjectLikesEntity.projectLikesEntity;

import com.eod.sitree.project.domain.modelRepository.ProjectLikesRepository;
import com.eod.sitree.project.infra.jpa_interfaces.ProjectLikesJpaRepository;
import com.eod.sitree.project.ui.dto.response.ProjectLikeInfoResponseDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProjectLikesRepositoryImpl implements ProjectLikesRepository {

    private final ProjectLikesJpaRepository projectLikesJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ProjectLikeInfoResponseDto getProjectLikeInfo(long projectId, Long memberId) {
        if (memberId == null) {
            Long count = projectLikesJpaRepository.countByProjectIdAndIsLikedIsTrue(projectId);
            return new ProjectLikeInfoResponseDto(count, false);
        }

        // 좋아요 수 쿼리
        JPAQuery<Long> totalLikesSub = jpaQueryFactory
                .select(projectLikesEntity.count())
                .from(projectLikesEntity)
                .where(projectLikesEntity.projectId.eq(projectId)
                        .and(projectLikesEntity.isLiked.isTrue()));

        // 사용자 좋아요 여부 서브쿼리
        JPAQuery<Boolean> isLikedSub = jpaQueryFactory
                .select(projectLikesEntity.isLiked.isTrue())
                .from(projectLikesEntity)
                .where(projectLikesEntity.memberId.eq(memberId)
                        .and(projectLikesEntity.projectId.eq(projectId)))
                .limit(1);

        // 최종 쿼리 (한방에 처리?)
        Tuple result = jpaQueryFactory
                .select(totalLikesSub, isLikedSub)
                .from(projectLikesEntity)
                .limit(1)
                .fetchOne();

        Long totalLikes = result.get(totalLikesSub) != null ? result.get(totalLikesSub) : 0L;
        Boolean isLiked = result.get(isLikedSub) != null ? result.get(isLikedSub) : false;
        return new ProjectLikeInfoResponseDto(totalLikes, isLiked);
    }
}
