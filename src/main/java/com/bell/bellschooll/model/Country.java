package com.bell.bellschooll.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Countries")
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(length = 100,nullable = false)
    private String name;
    @Column
    private Integer code;
}
