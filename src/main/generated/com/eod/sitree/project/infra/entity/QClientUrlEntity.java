package com.eod.sitree.project.infra.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClientUrlEntity is a Querydsl query type for ClientUrlEntity
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QClientUrlEntity extends BeanPath<ClientUrlEntity> {

    private static final long serialVersionUID = -695989144L;

    public static final QClientUrlEntity clientUrlEntity = new QClientUrlEntity("clientUrlEntity");

    public final MapPath<com.eod.sitree.project.domain.model.type.PlatformType, String, StringPath> downloadMethods = this.<com.eod.sitree.project.domain.model.type.PlatformType, String, StringPath>createMap("downloadMethods", com.eod.sitree.project.domain.model.type.PlatformType.class, String.class, StringPath.class);

    public final StringPath liveWebDomain = createString("liveWebDomain");

    public QClientUrlEntity(String variable) {
        super(ClientUrlEntity.class, forVariable(variable));
    }

    public QClientUrlEntity(Path<? extends ClientUrlEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClientUrlEntity(PathMetadata metadata) {
        super(ClientUrlEntity.class, metadata);
    }

}

