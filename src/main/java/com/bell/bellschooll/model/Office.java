package com.bell.bellschooll.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Offices")
@Data
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false,length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;

    @Column(name = "address",nullable = false,length = 255)
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
    private List<User> users;
}
