package com.app.fodappspringboot.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Session {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @OneToOne(targetEntity = AppUser.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AppUser appUser;

    @ManyToOne
    private CartItem cartItem;


}
