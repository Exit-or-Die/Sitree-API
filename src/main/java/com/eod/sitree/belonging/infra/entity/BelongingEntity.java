package com.eod.sitree.belonging.infra.entity;

import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.belonging.domain.model.BelongingType;
import com.eod.sitree.common.infra.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
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

    public BelongingEntity(Long belongingId, BelongingType belongingType, String name,
        String imageUrl) {

        this.belongingId = belongingId;
        this.belongingType = belongingType;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Belonging toDomainModel() {

        return new Belonging(belongingId, belongingType, name, imageUrl);
    }
}
