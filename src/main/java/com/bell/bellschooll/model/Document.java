package com.bell.bellschooll.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDate;

@Entity
@Table(name = "Document")
@Data
@ToString
public class Document {
    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Id
    private Integer id;

    @Column(length = 50)
    private String docNumber;

    @Column
    private LocalDate docDate;

    @ToString.Exclude
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "doc_type_id",nullable = false)
    private DocumentType docType;
}
