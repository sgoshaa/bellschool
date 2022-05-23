package com.bell.bellschooll.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
@DynamicUpdate
public class Organization {
    /**
     * Уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;
    /**
     * Название организации
     */
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    /**
     * Полное название организации
     */
    @NotNull
    @Size(min = 1, max = 255)
    private String fullName;
    /**
     * ИНН организации
     */
    @NotNull
    private Integer inn;
    /**
     * КПП организации
     */
    @NotNull
    private Integer kpp;
    /**
     * Адрес организации
     */
    @NotNull
    private String address;
    /**
     * Телефон организации
     */
    private String phone;
    /**
     * Поле isActive
     */
    private Boolean isActive;

    /**
     * Список офисов в организации
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Office> offices;
}
