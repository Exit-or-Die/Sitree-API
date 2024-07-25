package com.eod.sitree.project.infra.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProjectTechStackEntity is a Querydsl query type for ProjectTechStackEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectTechStackEntity extends EntityPathBase<ProjectTechStackEntity> {

    private static final long serialVersionUID = -180928805L;

    public static final QProjectTechStackEntity projectTechStackEntity = new QProjectTechStackEntity("projectTechStackEntity");

    public final NumberPath<Long> projectTechStackId = createNumber("projectTechStackId", Long.class);

    public final EnumPath<com.eod.sitree.project.domain.model.type.TechStackType> techStackType = createEnum("techStackType", com.eod.sitree.project.domain.model.type.TechStackType.class);

    public final NumberPath<Long> techviewId = createNumber("techviewId", Long.class);

    public QProjectTechStackEntity(String variable) {
        super(ProjectTechStackEntity.class, forVariable(variable));
    }

    public QProjectTechStackEntity(Path<? extends ProjectTechStackEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectTechStackEntity(PathMetadata metadata) {
        super(ProjectTechStackEntity.class, metadata);
    }

}

