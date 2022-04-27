package com.bell.bellschooll.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
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
    @NotNull
    private String name;
    /**
     * Код документа
     */
    @NotNull
    private String code;
}
