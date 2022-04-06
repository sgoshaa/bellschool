package com.bell.bellschooll.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;


@Data
@Entity
@Table(name = "Document_type")
public class DocumentType {
    @Version
    private Integer version;
    @Id
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String code;
}
