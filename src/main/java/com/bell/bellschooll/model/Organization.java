package com.bell.bellschooll.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.List;

@Entity
@Table(name = "Organization")
@Data
@ToString
public class Organization {
    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name",nullable = false ,length = 50)
    private String name;

    @Column(name = "full_name",nullable = false,length = 255)
    private String fullName;

    @Column(name = "inn",nullable = false)
    private Integer inn;

    @Column(name = "kpp",nullable = false)
    private Integer kpp;

    @Column(name = "address",nullable = false)
    private String address;

    @Column
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive;

    @ToString.Exclude
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Office> offices;
}
