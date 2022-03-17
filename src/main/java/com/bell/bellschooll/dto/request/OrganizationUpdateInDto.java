package com.bell.bellschooll.dto.request;
//In:
//        {
//        “id”:””, //обязательный параметр
//        “name”:””, //обязательный параметр
//        “fullName”:””, //обязательный параметр
//        “inn”:””, //обязательный параметр
//        “kpp”:””,  //обязательный параметр
//        “address”:””, //обязательный параметр
//        “phone”,””,
//        “isActive”:”true”
//        }

import lombok.Data;

@Data
public class OrganizationUpdateInDto {
    private Integer id;
    private String name;
    private String fullName;
    private Integer inn;
    private Integer kpp;
    private String address;
    private String phone;
    private Boolean isActive;
}
