package com.bell.bellschooll.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name="\"user\"")
public class User {
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
     * Внешний ключ на таблицу офис
     */
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;
    /**
     * Фамилия пользователя
     */
    @Size(min = 3,max = 50,message = "Размер поля firstName должен быть от 3 до 50 симолов.")
    @NotNull
    private String firstName;
    /**
     * Имя пользователя
     */
    @Size(min = 3,max = 50,message = "Размер поля secondName должен быть от 3 до 50 симолов.")
    @NotNull
    private String secondName;

    /**
     * Отчество пользователя
     */
    @Size(min = 0,max = 50,message = "Размер поля middleName должен быть от 0 до 50 симолов.")
    private String middleName;
    /**
     * Должность пользователя
     */
    @Size(min = 3,max = 50,message = "Размер поля position должен быть от 3 до 255 симолов.")
    @NotNull
    private String position;
    /**
     * Телефон пользователя
     */
    @Size(max = 25,message = "Размер поля position должен быть от 0 до 25 симолов.")
    private String phone;
    /**
     * Внешний ключ на таблицу документов
     */
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, optional = true,
            mappedBy = "user")
    private Document document;
    /**
     * Внешний ключ на таблицу стран
     */
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    /**
     * Поле isIdentified
     */
    private Boolean isIdentified;
}
