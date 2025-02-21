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
import com.eod.sitree.project.ui.dto.response.CategoryGetResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.HashMap;
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

        List<CategoryEntity> categoryEntities = jpaQueryFactory
                .select(categoryEntity)
                .from(categoryUsageEntity)
                .join(categoryEntity)
                .on(categoryUsageEntity.categoryId.eq(categoryEntity.categoryId))
                .where(categoryUsageEntity.projectId.eq(projectId))
                .fetch();
        return categoryEntities.stream().map(CategoryEntity::toDomainModel).toList();
    }

    @Override
    public void saveAllProjectCategoryIds(long projectId, List<Category> categories) {
        List<String> categoryNames = categories.stream().map(Category::getName).toList();
        List<CategoryEntity> categoryEntities = categoryJpaRepository.findAllByNameIn(
                categoryNames);
        if (categoryEntities.size() != categories.size()) {
            throw new ProjectException(ApplicationErrorType.NOT_EXIST_PROJECT_CATEGORY);
        }
        List<CategoryUsageEntity> categoryUsageEntities = categoryEntities.stream()
                .map(c -> new CategoryUsageEntity(projectId, c.getCategoryId())).toList();
        categoryUsageJpaRepository.saveAll(categoryUsageEntities);
    }

    @Override
    public List<CategoryGetResponseDto> getAllCategories() {
        return jpaQueryFactory
                .select(Projections.constructor(CategoryGetResponseDto.class,
                        categoryEntity.categoryId,
                        categoryEntity.name
                ))
                .from(categoryEntity)
                .fetch();
    }

    @Override
    public List<CategoryGetResponseDto> getAllUsingCategories() {
        return jpaQueryFactory
                .select(Projections.constructor(CategoryGetResponseDto.class,
                        categoryEntity.categoryId,
                        categoryEntity.name
                ))
                .from(categoryEntity)
                .join(categoryUsageEntity)
                .on(categoryEntity.categoryId.eq(categoryUsageEntity.categoryId))
                .groupBy(categoryEntity.categoryId)
                .fetch();
    }

    @Override
    public List<List<CategoryGetResponseDto>> getGroupedCategories() {
        HashMap<Long, List<CategoryGetResponseDto>> output = new HashMap<>();
        List<CategoryEntity> categoryEntities = jpaQueryFactory
                .selectFrom(categoryEntity)
                .fetch();
        categoryEntities.forEach(c -> {
            List<CategoryGetResponseDto> categoryGroup = output.getOrDefault(c.getGroupId(),
                    new ArrayList<>());
            categoryGroup.add(new CategoryGetResponseDto(c.getCategoryId(), c.getName()));
            output.put(c.getGroupId(), categoryGroup);
        });
        return output.values().stream().toList();
    }

    @Override
    public void updateProjectCategories(long projectId, List<Category> updateCategories) {
        // 현재 등록된 카테고리 사용 엔티티 조회
        List<CategoryUsageEntity> usingCategoryUsageEntities = categoryUsageJpaRepository.findAllByProjectId(
                projectId);

        // 업데이트할 카테고리 사용 정보 조회
        List<CategoryUsageEntity> updateCategoryUsageEntities = new ArrayList<>(
                categoryJpaRepository.findAllByNameIn(
                                updateCategories.stream().map(Category::getName).toList()
                        ).stream().map(c -> new CategoryUsageEntity(projectId, c.getCategoryId()))
                        .toList());

        // 등록되지 않은 카테고리가 있는경우
        if (updateCategories.size() != updateCategoryUsageEntities.size()) {
            throw new ProjectException(ApplicationErrorType.NOT_EXIST_PROJECT_CATEGORY);
        }

        // 변동 없는 카테고리 삭제
        updateCategoryUsageEntities.forEach(c -> {
            if (usingCategoryUsageEntities.contains(c)) {
                usingCategoryUsageEntities.remove(c);
                updateCategoryUsageEntities.remove(c);
            }
        });

        // 남아있는 사용중인 카테고리는 삭제
        if (!usingCategoryUsageEntities.isEmpty()) {
            categoryUsageJpaRepository.deleteAll(usingCategoryUsageEntities);
        }
        // 남아있는 업데이트 카테고리는 추가
        if (!updateCategoryUsageEntities.isEmpty()) {
            categoryUsageJpaRepository.saveAll(updateCategoryUsageEntities);
        }

    }

}
