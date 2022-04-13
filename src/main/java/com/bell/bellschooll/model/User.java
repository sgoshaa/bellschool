package com.bell.bellschooll.model;

import lombok.Data;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@Table(name = "User1")
@Data
@ToString
public class User {
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
     * Внешний ключ на таблицу офис
     */
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;
    /**
     * Фамилия пользователя
     */
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    /**
     * Имя пользователя
     */
    @Column(name = "second_name", length = 50)
    private String secondName;
    /**
     * Отчество пользователя
     */
    @Column(name = "middle_name", length = 50)
    private String middleName;
    /**
     * Должность пользователя
     */
    @Column(nullable = false, length = 255)
    private String position;
    /**
     * Телефон пользователя
     */
    @Column(length = 25)
    private String phone;
    /**
     * Внешний ключ на таблицу документов
     */
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, optional = true,
            mappedBy = "user")
    //@JoinColumn(name = "doc_id", nullable = false)
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
    @Column(name = "is_identified")
    private Boolean isIdentified;
}
