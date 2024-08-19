package com.eod.sitree.project.infra.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOverviewEntity is a Querydsl query type for OverviewEntity
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QOverviewEntity extends BeanPath<OverviewEntity> {

    private static final long serialVersionUID = -941523749L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOverviewEntity overviewEntity = new QOverviewEntity("overviewEntity");

    public final QClientUrlEntity clientUrl;

    public final StringPath detailDescription = createString("detailDescription");

    public final ListPath<com.eod.sitree.project.domain.model.Image, SimplePath<com.eod.sitree.project.domain.model.Image>> images = this.<com.eod.sitree.project.domain.model.Image, SimplePath<com.eod.sitree.project.domain.model.Image>>createList("images", com.eod.sitree.project.domain.model.Image.class, SimplePath.class, PathInits.DIRECT2);

    public final StringPath representImage = createString("representImage");

    public QOverviewEntity(String variable) {
        this(OverviewEntity.class, forVariable(variable), INITS);
    }

    public QOverviewEntity(Path<? extends OverviewEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOverviewEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOverviewEntity(PathMetadata metadata, PathInits inits) {
        this(OverviewEntity.class, metadata, inits);
    }

    public QOverviewEntity(Class<? extends OverviewEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clientUrl = inits.isInitialized("clientUrl") ? new QClientUrlEntity(forProperty("clientUrl")) : null;
    }

}

