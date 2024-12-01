package com.eod.sitree.belonging.infra.entity;

import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.belonging.domain.model.BelongingType;
import com.eod.sitree.belonging.domain.model.BelongingWithPoint;
import com.eod.sitree.common.infra.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "BELONGING")
public class BelongingEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long belongingId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BelongingType belongingType;

    @Column(nullable = false)
    private String name;

    private String imageUrl;

    private Long currentRanking;

    private Long prevRanking;

    public BelongingEntity(Long belongingId, BelongingType belongingType, String name,
        String imageUrl, Long currentRanking, Long prevRanking) {

        this.belongingId = belongingId;
        this.belongingType = belongingType;
        this.name = name;
        this.imageUrl = imageUrl;
        this.currentRanking = currentRanking;
        this.prevRanking = prevRanking;
    }

    public BelongingEntity(BelongingWithPoint belongingWithPoint) {

        this.belongingId = belongingWithPoint.getBelongingId();
        this.belongingType = belongingWithPoint.getType();
        this.name = belongingWithPoint.getName();
        this.imageUrl = belongingWithPoint.getImageUrl();
        this.currentRanking = belongingWithPoint.getCurrentRanking();
        this.prevRanking = belongingWithPoint.getPrevRanking();
    }

    public Belonging toDomainModel() {

        return new Belonging(belongingId, belongingType, name, imageUrl, currentRanking, prevRanking);
    }

    public BelongingEntity updateRankingAndReturn(long newRanking) {

        this.prevRanking = currentRanking;
        this.currentRanking = newRanking;

        return this;
    }
}
