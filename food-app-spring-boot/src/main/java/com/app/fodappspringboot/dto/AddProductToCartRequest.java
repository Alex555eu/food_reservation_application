package com.app.fodappspringboot.dto;

import java.util.UUID;

public record AddProductToCartRequest(
        UUID productId,
        Integer quantity
) {
}
