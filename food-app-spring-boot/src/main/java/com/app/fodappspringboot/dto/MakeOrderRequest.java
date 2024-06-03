package com.app.fodappspringboot.dto;

import java.util.UUID;

public record MakeOrderRequest(
        UUID storeId,
        String text
) {
}
