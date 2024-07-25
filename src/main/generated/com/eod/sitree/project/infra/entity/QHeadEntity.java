package com.eod.sitree.project.infra.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHeadEntity is a Querydsl query type for HeadEntity
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QHeadEntity extends BeanPath<HeadEntity> {

    private static final long serialVersionUID = 19733378L;

    public static final QHeadEntity headEntity = new QHeadEntity("headEntity");

    public final StringPath healthCheckUrl = createString("healthCheckUrl");

    public final StringPath shortDescription = createString("shortDescription");

    public final StringPath thumbnailImageUrl = createString("thumbnailImageUrl");

    public final StringPath title = createString("title");

    public QHeadEntity(String variable) {
        super(HeadEntity.class, forVariable(variable));
    }

    public QHeadEntity(Path<? extends HeadEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHeadEntity(PathMetadata metadata) {
        super(HeadEntity.class, metadata);
    }

}

