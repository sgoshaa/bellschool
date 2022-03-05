package com.bell.bellschooll.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    //@ManyToMany
    @Column(name = "doc_id",nullable = false)
    private Integer docId;
}
