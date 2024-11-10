package com.eod.sitree.project.ui.dto.request;

import static com.eod.sitree.comment.infra.entity.QCommentEntity.commentEntity;
import static com.eod.sitree.project.infra.entity.QProjectEntity.projectEntity;
import static com.eod.sitree.project.infra.entity.QProjectLikesEntity.projectLikesEntity;

import com.eod.sitree.common.request.PageRequestDto;
import com.querydsl.core.types.Expression;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectListRequestDto extends PageRequestDto {
    private SortType sortType = SortType.LATEST;

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
