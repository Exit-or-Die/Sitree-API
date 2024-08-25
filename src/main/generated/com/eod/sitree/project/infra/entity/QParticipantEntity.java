package com.eod.sitree.project.infra.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QParticipantEntity is a Querydsl query type for ParticipantEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParticipantEntity extends EntityPathBase<ParticipantEntity> {

    private static final long serialVersionUID = -783604105L;

    public static final QParticipantEntity participantEntity = new QParticipantEntity("participantEntity");

    public final BooleanPath isLeader = createBoolean("isLeader");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> participantId = createNumber("participantId", Long.class);

    public final StringPath position = createString("position");

    public final NumberPath<Long> projectId = createNumber("projectId", Long.class);

    public QParticipantEntity(String variable) {
        super(ParticipantEntity.class, forVariable(variable));
    }

    public QParticipantEntity(Path<? extends ParticipantEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParticipantEntity(PathMetadata metadata) {
        super(ParticipantEntity.class, metadata);
    }

}

