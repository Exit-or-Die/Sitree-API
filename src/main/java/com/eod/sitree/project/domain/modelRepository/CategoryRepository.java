package com.eod.sitree.project.domain.modelRepository;

import com.eod.sitree.project.domain.model.Category;
import com.eod.sitree.project.ui.dto.response.CategoryGetResponseDto;
import java.util.List;

public interface CategoryRepository {

    List<Category> findAllByProjectId(long projectId);


    void saveAllProjectCategoryIds(long projectId, List<Category> categories);

    List<CategoryGetResponseDto> getAllCategories();

    List<CategoryGetResponseDto> getAllUsingCategories();
}
