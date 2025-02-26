package com.eod.sitree.project.ui.dto.request;

import static com.eod.sitree.comment.infra.entity.QCommentEntity.commentEntity;
import static com.eod.sitree.project.infra.entity.QProjectEntity.projectEntity;
import static com.eod.sitree.project.infra.entity.QProjectLikesEntity.projectLikesEntity;

import com.eod.sitree.common.request.BasePageRequest;
import com.querydsl.core.types.Expression;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class ProjectListRequestDto extends BasePageRequest {
    private SortType sortType;
    private List<Long> categoryIds;
    private String nameKeyword;

    public ProjectListRequestDto(Integer pageNo, Integer size, SortType sortType,
        List<Long> categoryIds, String nameKeyword) {
        super(pageNo, size);
        this.sortType = sortType == null ? SortType.LATEST : sortType ;
        this.categoryIds = categoryIds == null? new ArrayList<>() : categoryIds;
        this.nameKeyword = nameKeyword == null ? "" : nameKeyword;
    }

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
