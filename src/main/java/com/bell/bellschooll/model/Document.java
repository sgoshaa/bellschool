package com.bell.bellschooll.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Documents")
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
