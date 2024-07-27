package com.eod.sitree.project.infra.entity;

import com.eod.sitree.project.domain.model.Head;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class HeadEntity {
    @Column(nullable = false)
    private String thumbnailImageUrl;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String shortDescription;
    @Column(nullable = false)
    private String healthCheckUrl;

    public HeadEntity(Head head) {
        this.thumbnailImageUrl = head.getThumbnailImageUrl();
        this.title = head.getTitle();
        this.shortDescription = head.getShortDescription();
        this.healthCheckUrl = head.getHealthCheckUrl();
    }
}
