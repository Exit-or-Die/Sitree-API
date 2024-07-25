package com.eod.sitree.project.infra.entity;

import com.eod.sitree.common.converter.HashMapConverter;
import com.eod.sitree.common.converter.ListConverter;
import com.eod.sitree.project.domain.model.Image;
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


@Entity
@Table(name="OVERVIEW")
public class OverviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long overviewId;

    @Column(nullable = false)
    private String representImage;

    @Column(nullable = false)
    @Convert(converter = ListConverter.class)
    private List<Image> images;

    @Embedded
    @Column(nullable = false)
    private ClientUrlEntity clientUrl;

    private String detailDescription; // 상세 소개
}
