package com.app.fodappspringboot.dto;

import lombok.Data;

import java.util.List;

@Data
public class AllOrdersResponseDto {

    private String placementDate;

    private String email;

    private String total;

    private String info;

    private String progress;

    private String products;

}


