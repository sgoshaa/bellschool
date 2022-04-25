package com.bell.bellschooll.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Country {

    /**
     * уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;
    /**
     * Название страны
     */
    @Column(length = 100, nullable = false)
    private String name;
    /**
     * Код страны
     */
    @Column
    private String code;
}
