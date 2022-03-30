package com.example.commandapi.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = 5930276498475533464L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column
    @Temporal(value = TemporalType.DATE)
    Date createdAt = new Date();
}
