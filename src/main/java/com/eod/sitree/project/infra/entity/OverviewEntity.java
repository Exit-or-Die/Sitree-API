package com.eod.sitree.project.infra.entity;

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
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
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

    public Overview toDomainModel(){
        List<Image> domainModelImages = new ArrayList<>();
        domainModelImages.add(new Image(this.representImage, ImageType.REPRESENT));
        domainModelImages.addAll(this.images);
        return new Overview(domainModelImages, clientUrl.toDomainModel(), this.detailDescription);
    }
}
