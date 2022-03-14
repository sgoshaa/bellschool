package com.bell.bellschooll.exception;
//{
//        “error”:”текст ошибки”
//        }

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseException {
    @JsonProperty
    private String error;
}
