package com.example.commons.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public abstract class AbstractBaseDTO implements Serializable {
    private static final long serialVersionUID = -4122733956278719099L;

    String id;
    Date createdAt;
}
