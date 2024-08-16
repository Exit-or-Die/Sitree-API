package com.eod.sitree.member.infra.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberEntity is a Querydsl query type for MemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberEntity extends EntityPathBase<MemberEntity> {

    private static final long serialVersionUID = -1863691825L;

    public static final QMemberEntity memberEntity = new QMemberEntity("memberEntity");

    public final com.eod.sitree.common.infra.entity.QBaseEntity _super = new com.eod.sitree.common.infra.entity.QBaseEntity(this);

    public final StringPath belonging = createString("belonging");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> memberNo = createNumber("memberNo", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath nickname = createString("nickname");

    public final StringPath profileImgUrl = createString("profileImgUrl");

    public final EnumPath<com.eod.sitree.member.domain.model.Provider> provider = createEnum("provider", com.eod.sitree.member.domain.model.Provider.class);

    public final StringPath thirdPartyProfileUrl = createString("thirdPartyProfileUrl");

    public QMemberEntity(String variable) {
        super(MemberEntity.class, forVariable(variable));
    }

    public QMemberEntity(Path<? extends MemberEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberEntity(PathMetadata metadata) {
        super(MemberEntity.class, metadata);
    }

}

