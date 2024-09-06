package com.eod.sitree.project.infra;

import static com.eod.sitree.project.infra.entity.QCategoryEntity.categoryEntity;
import static com.eod.sitree.project.infra.entity.QCategoryUsageEntity.categoryUsageEntity;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.project.domain.model.Category;
import com.eod.sitree.project.domain.modelRepository.CategoryRepository;
import com.eod.sitree.project.exeption.ProjectException;
import com.eod.sitree.project.infra.entity.CategoryEntity;
import com.eod.sitree.project.infra.entity.CategoryUsageEntity;
import com.eod.sitree.project.infra.jpa_interfaces.CategoryJpaRepository;
import com.eod.sitree.project.infra.jpa_interfaces.CategoryUsageJpaRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final CategoryJpaRepository categoryJpaRepository;
    private final CategoryUsageJpaRepository categoryUsageJpaRepository;

    @Override
    public List<Category> findAllByProjectId(long projectId) {
        return jpaQueryFactory
                .select(Projections.fields(Category.class,
                        categoryEntity.name.as("name")
                ))
                .from(categoryUsageEntity)
                .join(categoryEntity)
                    .on(categoryUsageEntity.categoryId.eq(categoryEntity.categoryId))
                .where(categoryUsageEntity.projectId.eq(projectId))
                .fetch();
    }

    @Override
    public void saveAllProjectCategoryIds(long projectId, List<Category> categories) {
        List<String> categoryNames = categories.stream().map(Category::getName).toList();
        List<CategoryEntity> categoryEntities = categoryJpaRepository.findAllByNameIn(categoryNames);
        if (categoryEntities.size() != categories.size()) {
            throw new ProjectException(ApplicationErrorType.NOT_EXIST_PROJECT_CATEGORY);
        }
        List<CategoryUsageEntity> categoryUsageEntities = categoryEntities.stream()
                .map(c -> new CategoryUsageEntity(projectId, c.getCategoryId())).toList();
        categoryUsageJpaRepository.saveAll(categoryUsageEntities);
    }
}
