package com.bell.bellschooll.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Document")
@Getter
@Setter
@Data
@ToString
public class Document {
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
     * Номер документа
     */
    @Column(length = 50)
    private String docNumber;
    /**
     * Дата документа
     */
    @Column
    private LocalDate docDate;
    /**
     * Внешний ключ на таблицу юзер
     */
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "doc_type_id", nullable = false)
    private DocumentType docType;
}
