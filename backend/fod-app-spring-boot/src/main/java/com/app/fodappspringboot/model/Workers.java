package com.app.fodappspringboot.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Entity
public class Workers {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @OneToOne(targetEntity = AppUser.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AppUser appUser;

    @OneToOne(targetEntity = Store.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Store store;

}
