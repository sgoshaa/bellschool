package com.bell.bellschooll.util;

import com.bell.bellschooll.model.Organization;

public class OrganizationHelper {
    public static Organization getNewOrganization(){
        Organization organization = new Organization();
        organization.setAddress("address");
        organization.setFullName("full_name");
        organization.setInn(1111111);
        organization.setKpp(2222222);
        organization.setIsActive(true);
        organization.setName("name");
        organization.setPhone("phone");
        return organization;
    }
}
