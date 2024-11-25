package com.eod.sitree.project.ui;

import com.eod.sitree.auth.support.AuthNotRequired;
import com.eod.sitree.common.response.ResponseDto;
import com.eod.sitree.project.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @AuthNotRequired
    @GetMapping
    public ResponseDto<?> getAllUsingCategories(){
        var result = categoryService.getAllUsingCategories();
        return ResponseDto.ok(result);
    }

    @AuthNotRequired
    @GetMapping("/all")
    public ResponseDto<?> getAllCategories(){
        var result = categoryService.getAllCategories();
        return ResponseDto.ok(result);
    }

    @AuthNotRequired
    @GetMapping("/grouped")
    public ResponseDto<?> getGroupedCategories(){
        var result = categoryService.getGroupedCategories();
        return ResponseDto.ok(result);
    }
}
