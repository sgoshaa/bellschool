package com.bell.bellschooll.util;

import com.bell.bellschooll.model.Country;
import com.bell.bellschooll.model.Document;
import com.bell.bellschooll.model.DocumentType;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.User;

import java.time.LocalDate;

public class UserHelper {
    public static User createUser(DocumentType documentType, Office office, Country country) {
        User user = new User();
        Document document = new Document();
        document.setUser(user);
        document.setDocType(documentType);
        document.setDate(LocalDate.now());
        document.setNumber("12120 322323");

        user.setDocument(document);
        user.setCountry(country);
        user.setOffice(office);
        user.setFirstName("first_name");
        user.setPosition("position");
        user.setMiddleName("middle_name");
        user.setIsIdentified(true);
        user.setSecondName("second_name");
        user.setPhone("phone");
        return user;
    }
}
