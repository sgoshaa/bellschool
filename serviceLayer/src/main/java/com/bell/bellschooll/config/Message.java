package com.bell.bellschooll.config;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {
    private int id;
    private String method;
    private Object body;
}
