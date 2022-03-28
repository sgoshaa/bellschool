package com.bell.bellschooll.dto.response;

import lombok.Data;
//{
//        “data”:{
//        //то, что в параметре out
//        }
//        }


@Data
public class ResponseDto<T> {
    T data;
}
