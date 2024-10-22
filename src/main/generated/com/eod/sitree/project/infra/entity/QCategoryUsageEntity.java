package com.eod.sitree.project.infra.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategoryUsageEntity is a Querydsl query type for CategoryUsageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryUsageEntity extends EntityPathBase<CategoryUsageEntity> {

    private static final long serialVersionUID = -1756770745L;

    public static final QCategoryUsageEntity categoryUsageEntity = new QCategoryUsageEntity("categoryUsageEntity");

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public final NumberPath<Long> categoryUsageId = createNumber("categoryUsageId", Long.class);

    public final NumberPath<Long> projectId = createNumber("projectId", Long.class);

    public QCategoryUsageEntity(String variable) {
        super(CategoryUsageEntity.class, forVariable(variable));
    }

    public QCategoryUsageEntity(Path<? extends CategoryUsageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryUsageEntity(PathMetadata metadata) {
        super(CategoryUsageEntity.class, metadata);
    }

}

