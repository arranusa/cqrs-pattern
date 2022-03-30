package com.example.commons.event;

import com.example.commons.message.Payload;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

import java.util.Date;

@Value
@Builder(builderClassName = "builder", toBuilder = true)
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(builder = ProductEvent.builder.class)
public class ProductEvent implements Payload {

    String id;
    String sku;
    String name;
    Double price;
    Integer stock;
    Date createdAt;

    @JsonPOJOBuilder(withPrefix = "")
    public static class builder {
    }
}
