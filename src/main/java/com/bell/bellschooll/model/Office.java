package com.bell.bellschooll.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Office {
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
     * Название офиса
     */
    @Column(nullable = false,length = 255)
    private String name;
    /**
     * Организация,внешний ключ таблицы организация
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;
    /**
     * Адрес офиса
     */
    @Column(name = "address",nullable = false,length = 255)
    private String address;
    /**
     * Телефон офиса
     */
    @Column(name = "phone")
    private String phone;
    /**
     * Поле isActive
     */
    @Column(name = "is_active")
    private Boolean isActive;
    /**
     * Список сотрудников
     */
    @ToString.Exclude
    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
    private List<User> users;
}
