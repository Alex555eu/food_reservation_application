package com.app.fodappspringboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;



public enum Role {

    USER,
    ADMIN

}
