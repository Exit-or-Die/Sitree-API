package com.eod.sitree.project.infra.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CategoryUsageEntityTest {

    @Test
    public void euqals_비교_테스트(){
        CategoryUsageEntity categoryUsageEntity1 = new CategoryUsageEntity(1L, 2L, 3L);
        CategoryUsageEntity categoryUsageEntity2 = new CategoryUsageEntity(1L, 2L, 3L);
        assertThat(categoryUsageEntity2.equals(categoryUsageEntity1)).isEqualTo(true);
    }
}