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
    /**
     * Уникальный идентификатор
     */
    @Id
    private Integer id;
    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;
    /**
     * Название документа
     */
    @Column(nullable = false)
    private String name;
    /**
     * Код документа
     */
    @Column(nullable = false)
    private String code;
}
