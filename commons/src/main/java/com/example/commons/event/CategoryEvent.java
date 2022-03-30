package com.example.commons.event;

import com.example.commons.dto.command.CategoryDTO;
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
@JsonDeserialize(builder = CategoryEvent.builder.class)
public class CategoryEvent implements Payload {

    String id;
    String name;
    Date createdAt;

    @JsonPOJOBuilder(withPrefix = "")
    public static class builder {
    }
}
