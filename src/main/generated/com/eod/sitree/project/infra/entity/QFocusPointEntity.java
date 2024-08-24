package com.eod.sitree.project.infra.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFocusPointEntity is a Querydsl query type for FocusPointEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFocusPointEntity extends EntityPathBase<FocusPointEntity> {

    private static final long serialVersionUID = 1482721274L;

    public static final QFocusPointEntity focusPointEntity = new QFocusPointEntity("focusPointEntity");

    public final StringPath focusedOn = createString("focusedOn");

    public final NumberPath<Long> focusPointId = createNumber("focusPointId", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> techviewId = createNumber("techviewId", Long.class);

    public QFocusPointEntity(String variable) {
        super(FocusPointEntity.class, forVariable(variable));
    }

    public QFocusPointEntity(Path<? extends FocusPointEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFocusPointEntity(PathMetadata metadata) {
        super(FocusPointEntity.class, metadata);
    }

}

