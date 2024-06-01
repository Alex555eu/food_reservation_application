package com.app.fodappspringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private BigDecimal total;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "placement_date")
    private LocalDateTime placementDate;

    @Column(name = "additional_info")
    private String additionalInfo;

/*    @OneToMany(mappedBy = "orderDetails", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderItem> orderItems;*/

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private Integer progressStatus;

}
