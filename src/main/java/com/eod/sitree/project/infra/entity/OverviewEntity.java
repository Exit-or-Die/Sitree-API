package com.eod.sitree.project.infra.entity;

import com.eod.sitree.common.converter.HashMapConverter;
import com.eod.sitree.common.converter.ListConverter;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.project.domain.model.Image;
import com.eod.sitree.project.domain.model.Overview;
import com.eod.sitree.project.domain.model.type.ImageType;
import com.eod.sitree.project.exeption.ProjectException;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import lombok.NoArgsConstructor;


@Embeddable
@NoArgsConstructor
public class OverviewEntity {

    @Column(nullable = false)
    private String representImage;

    @Column(nullable = false)
    @Convert(converter = ListConverter.class)
    private List<Image> images;

    @Embedded
    @Column(nullable = false)
    private ClientUrlEntity clientUrl;

    private String detailDescription; // 상세 소개

    public OverviewEntity(Overview overview) {
        Image representImage = overview.getImages().stream().filter(i -> i.getImageType().isRepresentImage())
                .findFirst().orElseThrow(() -> new ProjectException(ApplicationErrorType.NO_REPRESENT_IMAGE));
        this.representImage = representImage.getImageUrl();
        this.images = overview.getImages().stream().filter(i -> i.getImageType().isBackgroundImage()).toList();
        this.clientUrl = new ClientUrlEntity(overview.getClientUrl());
        this.detailDescription = overview.getDetailDescription();
    }

}
