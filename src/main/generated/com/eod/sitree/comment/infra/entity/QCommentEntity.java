package com.eod.sitree.comment.infra.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentEntity is a Querydsl query type for CommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentEntity extends EntityPathBase<CommentEntity> {

    private static final long serialVersionUID = -1051623907L;

    public static final QCommentEntity commentEntity = new QCommentEntity("commentEntity");

    public final com.eod.sitree.common.infra.entity.QBaseEntity _super = new com.eod.sitree.common.infra.entity.QBaseEntity(this);

    public final ListPath<CommentEntity, QCommentEntity> childComments = this.<CommentEntity, QCommentEntity>createList("childComments", CommentEntity.class, QCommentEntity.class, PathInits.DIRECT2);

    public final NumberPath<Long> commentId = createNumber("commentId", Long.class);

    public final EnumPath<com.eod.sitree.comment.domain.model.CommentType> commentType = createEnum("commentType", com.eod.sitree.comment.domain.model.CommentType.class);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> createMemberId = createNumber("createMemberId", Long.class);

    public final BooleanPath isChildComment = createBoolean("isChildComment");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> parentCommentId = createNumber("parentCommentId", Long.class);

    public final NumberPath<Long> targetId = createNumber("targetId", Long.class);

    public QCommentEntity(String variable) {
        super(CommentEntity.class, forVariable(variable));
    }

    public QCommentEntity(Path<? extends CommentEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommentEntity(PathMetadata metadata) {
        super(CommentEntity.class, metadata);
    }

}

