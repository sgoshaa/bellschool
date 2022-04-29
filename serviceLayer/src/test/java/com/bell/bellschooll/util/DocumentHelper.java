package com.bell.bellschooll.util;

import com.bell.bellschooll.model.DocumentType;

public class DocumentHelper {

    public static DocumentType createDocumentType() {
        DocumentType documentType = new DocumentType();
        documentType.setId(ConstantValue.ID);
        documentType.setCode("21");
        documentType.setName("Паспорт");
        return documentType;
    }
}
