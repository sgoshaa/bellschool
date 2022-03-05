package com.bell.bellschooll.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Organizations")
@Data
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Office> offices;
}
