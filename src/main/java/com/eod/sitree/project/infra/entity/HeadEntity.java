package com.eod.sitree.project.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class HeadEntity {
    @Column(nullable = false)
    private String thumbnailImageUrl;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String shortDescription;
    @Column(nullable = false)
    private String healthCheckUrl;
}
