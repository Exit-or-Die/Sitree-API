package com.eod.sitree.project.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FOCUS_POINT")
public class FocusPointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long focusPointId;

    @Column(nullable = false)
    private Long memberNo;

    @Column(nullable = false)
    private Long techviewId;

    @Column(nullable = false)
    private String focusedOn;
}
