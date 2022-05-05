package com.bell.bellschooll.util;

import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;

public class OfficeHelper {
    public static Office createOffice(Organization organization) {
        Office office = new Office();
        office.setOrganization(organization);
        office.setIsActive(true);
        office.setName("name");
        office.setAddress("address");
        office.setPhone("phone");
        return office;
    }
}
