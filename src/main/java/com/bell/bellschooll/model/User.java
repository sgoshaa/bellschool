package com.bell.bellschooll.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "User")
@Data
public class User {
    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    @Column(name = "first_name",nullable = false,length = 50)
    private String firstName;

    @Column(name = "second_name",length = 50)
    private String secondName;

    @Column(name = "middle_name",length = 50)
    private String middleName;

    @Column(nullable = false, length = 255)
    private String position;

    @Column(length = 25)
    private String phone;

    @OneToOne
    @JoinColumn(name = "doc_id",nullable = false)
    private Document docId;

    @ManyToOne
    @JoinColumn(name = "country_id",nullable = false)
    private Country country ;
}
