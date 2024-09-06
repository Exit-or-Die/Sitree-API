package com.eod.sitree.project.service;

import com.eod.sitree.project.domain.modelRepository.CategoryRepository;
import com.eod.sitree.project.ui.dto.response.CategoryGetResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryGetResponseDto> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public List<CategoryGetResponseDto> getAllUsingCategories() {
        return categoryRepository.getAllUsingCategories();
    }
}
