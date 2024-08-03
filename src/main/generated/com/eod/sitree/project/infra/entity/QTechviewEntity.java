package com.eod.sitree.project.infra.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTechviewEntity is a Querydsl query type for TechviewEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTechviewEntity extends EntityPathBase<TechviewEntity> {

    private static final long serialVersionUID = 2050013213L;

    public static final QTechviewEntity techviewEntity = new QTechviewEntity("techviewEntity");

    public final StringPath architectureDescription = createString("architectureDescription");

    public final StringPath architectureImageUrl = createString("architectureImageUrl");

    public final StringPath gitRepositoryUrl = createString("gitRepositoryUrl");

    public final NumberPath<Long> projectId = createNumber("projectId", Long.class);

    public final StringPath techArea = createString("techArea");

    public final NumberPath<Long> techviewId = createNumber("techviewId", Long.class);

    public QTechviewEntity(String variable) {
        super(TechviewEntity.class, forVariable(variable));
    }

    public QTechviewEntity(Path<? extends TechviewEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTechviewEntity(PathMetadata metadata) {
        super(TechviewEntity.class, metadata);
    }

}

