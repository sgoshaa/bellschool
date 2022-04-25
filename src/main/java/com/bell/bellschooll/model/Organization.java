package com.bell.bellschooll.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
public class Organization {
    /**
     * Уникальный идентификатор
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
     * Название организации
     */
    @Column(name = "name",nullable = false ,length = 50)
    private String name;
    /**
     * Полное название организации
     */
    @Column(name = "full_name",nullable = false,length = 255)
    private String fullName;
    /**
     * ИНН организации
     */
    @Column(name = "inn",nullable = false)
    private Integer inn;
    /**
     * КПП организации
     */
    @Column(name = "kpp",nullable = false)
    private Integer kpp;
    /**
     * Адрес организации
     */
    @Column(name = "address",nullable = false)
    private String address;
    /**
     * Телефон организации
     */
    @Column
    private String phone;
    /**
     * Поле isActive
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Список офисов в организации
     */
    @ToString.Exclude
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Office> offices;
}
