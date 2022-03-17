package com.bell.bellschooll.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDate;

@Entity
@Table(name = "Document")
@Data
public class Document {
    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "doc_code")
    private Integer docCode;

    @Column(name = "doc_name",length = 255)
    private String docName;

    @Column(name = "doc_number",length = 50)
    private String docNumber;

    @Column(name = "doc_date")
    private LocalDate docDate;

    @Column(name = "citizenship_сode")
    private String citizenshipCode;
}
