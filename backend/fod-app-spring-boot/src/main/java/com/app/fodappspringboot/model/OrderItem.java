package com.app.fodappspringboot.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;

    private Double quantity;

    @Column(columnDefinition = "COMMENT '" +
            "0 - item in cart" +
            "1 - item ordered" +
            "2 - order received'")
    private Integer progressStatus;

}
