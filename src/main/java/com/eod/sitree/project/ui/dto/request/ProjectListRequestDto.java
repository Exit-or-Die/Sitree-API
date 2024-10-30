package com.eod.sitree.project.ui.dto.request;

import static com.eod.sitree.comment.infra.entity.QCommentEntity.commentEntity;
import static com.eod.sitree.project.infra.entity.QProjectEntity.projectEntity;
import static com.eod.sitree.project.infra.entity.QProjectLikesEntity.projectLikesEntity;

import com.querydsl.core.types.Expression;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectListRequestDto {
    private SortType sortType;
    private int pageNo;

    @Getter
    public enum SortType {
        LATEST(projectEntity.modifiedAt),
        LIKES(projectLikesEntity.likesId.countDistinct()),
        COMMENTS(commentEntity.commentId.countDistinct()),
        VIEWS(projectEntity.viewCount);

        private final Expression<?> tClass;

        SortType(Expression<?> tClass) {
            this.tClass = tClass;
        }
    }
}
