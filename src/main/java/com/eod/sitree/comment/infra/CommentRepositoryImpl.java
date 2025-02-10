package com.eod.sitree.comment.infra;

import static com.eod.sitree.comment.infra.entity.QCommentEntity.commentEntity;

import com.eod.sitree.comment.domain.model.Comment;
import com.eod.sitree.comment.domain.model.CommentType;
import com.eod.sitree.comment.domain.modelrepository.CommentRepository;
import com.eod.sitree.comment.exception.CommentException;
import com.eod.sitree.comment.infra.entity.CommentEntity;
import com.eod.sitree.comment.infra.entity.QCommentEntity;
import com.eod.sitree.comment.ui.dto.response.CommentResponseDto;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.infra.entity.MemberEntity;
import com.eod.sitree.member.infra.entity.QMemberEntity;
import com.eod.sitree.project.infra.entity.QParticipantEntity;
import com.eod.sitree.project.infra.entity.QProjectEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public CommentRepositoryImpl(CommentJpaRepository commentJpaRepository,
        JPAQueryFactory jpaQueryFactory) {
        super(Comment.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.commentJpaRepository = commentJpaRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentJpaRepository.save(new CommentEntity(comment)).toDomainModel();
    }

    @Override
    public Comment findByCommentId(Long commentId) {

        return Optional.ofNullable(commentJpaRepository.findByCommentId(commentId))
            .orElseThrow(() -> new CommentException(ApplicationErrorType.COMMENT_NOT_FOUND))
            .toDomainModel();
    }

    @Override
    public Boolean existParentByParentCommentId(Long parentCommentId, CommentType commentType) {
        return commentJpaRepository.findByCommentIdAndIsChildCommentAndCommentType(parentCommentId,
            false, commentType) != null;
    }

    @Override
    public List<Comment> findByProjectId(Long projectId) {

        return Optional.ofNullable(
                commentJpaRepository.findAllByCommentTypeAndTargetIdAndIsChildComment(
                    CommentType.PROJECT, projectId, false))
            .orElseGet(ArrayList::new)
            .stream().map(CommentEntity::toDomainModel)
            .toList();
    }

    @Override
    public List<CommentResponseDto> findChildCommentsByProjectIdAndParentCommentId(Long projectId,
        Long parentCommentId) {

        QMemberEntity memberEntity = new QMemberEntity("memberEntity");
        QParticipantEntity participantEntity = new QParticipantEntity("participantEntity");

        return jpaQueryFactory.select(
                Projections.constructor(
                    CommentResponseDto.class,
                    commentEntity.commentId,
                    commentEntity.commentType,
                    commentEntity.targetId,
                    commentEntity.contents,
                    memberEntity.memberId,
                    memberEntity.nickname,
                    memberEntity.profileImgUrl,
                    new CaseBuilder().when(participantEntity.isLeader.isNull())
                        .then(false)
                        .otherwise(participantEntity.isLeader),
                    new CaseBuilder().when(participantEntity.participantId.isNull())
                        .then(false)
                        .otherwise(true),
                    commentEntity.parentCommentId,
                    commentEntity.isChildComment,
                    commentEntity.isDeleted
                )
            )
            .from(commentEntity)
            .innerJoin(memberEntity)
            .on(commentEntity.createMemberId.eq(memberEntity.memberId))
            .leftJoin(participantEntity)
            .on(
                participantEntity.projectId.eq(commentEntity.targetId),
                participantEntity.memberId.eq(commentEntity.createMemberId)
            )
            .where(
                commentEntity.commentType.eq(CommentType.PROJECT),
                commentEntity.targetId.eq(projectId),
                commentEntity.parentCommentId.eq(parentCommentId),
                commentEntity.isChildComment.eq(true),
                commentEntity.isDeleted.eq(false)
            )
            .fetch();
    }

    @Override
    public Page<Comment> findByProjectIdAsPage(Long projectId, Pageable pageable) {
        JPQLQuery<CommentEntity> query = getQuerydsl().applyPagination(
            pageable,
            jpaQueryFactory.selectFrom(commentEntity)
                .where(
                    commentEntity.commentType.eq(CommentType.PROJECT),
                    commentEntity.targetId.eq(projectId),
                    commentEntity.isChildComment.eq(false)
                )
        );

        return new PageImpl<>(
            query.fetch().stream().map(CommentEntity::toDomainModel).toList(),
            pageable,
            query.fetchCount()
        );
    }

    @Override
    public Page<CommentResponseDto> findDtoByProjectIdAsPage(Long projectId, Pageable pageable) {

        QMemberEntity memberEntity = new QMemberEntity("memberEntity");
        QParticipantEntity participantEntity = new QParticipantEntity("participantEntity");

        JPQLQuery<CommentResponseDto> query = getQuerydsl().applyPagination(
            pageable,
            jpaQueryFactory.select(
                    Projections.constructor(
                        CommentResponseDto.class,
                        commentEntity.commentId,
                        commentEntity.commentType,
                        commentEntity.targetId,
                        commentEntity.contents,
                        memberEntity.memberId,
                        memberEntity.nickname,
                        memberEntity.profileImgUrl,
                        new CaseBuilder().when(participantEntity.isLeader.isNull())
                            .then(false)
                            .otherwise(participantEntity.isLeader),
                        new CaseBuilder().when(participantEntity.participantId.isNull())
                            .then(false)
                            .otherwise(true),
                        commentEntity.parentCommentId,
                        commentEntity.isChildComment,
                        commentEntity.isDeleted
                    )
                )
                .from(commentEntity)
                .innerJoin(memberEntity)
                .on(commentEntity.createMemberId.eq(memberEntity.memberId))
                .leftJoin(participantEntity)
                .on(
                    participantEntity.projectId.eq(commentEntity.targetId),
                    participantEntity.memberId.eq(commentEntity.createMemberId)
                )
                .where(
                    commentEntity.commentType.eq(CommentType.PROJECT),
                    commentEntity.targetId.eq(projectId),
                    commentEntity.isChildComment.eq(false),
                    commentEntity.isDeleted.eq(false)
                )
        );

        return new PageImpl<>(
            query.fetch(),
            pageable,
            query.fetchCount()
        );
    }

    @Override
    public Boolean existsByCommentId(Long commentId) {

        return commentJpaRepository.existsById(commentId);
    }
}
