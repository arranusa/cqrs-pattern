package com.example.queryapi.route;

import com.example.queryapi.service.CategoryQueryService;
import com.example.queryapi.service.ProductQueryService;
import org.springframework.stereotype.Component;
import org.apache.camel.builder.RouteBuilder;

@Component
public class CategoryEventRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:topic:T.CMD.CategoryCreated?messageConverter=#jacksonJmsMessageConverter")
                .bean(CategoryQueryService.class, "saveCategory" );
    }
}
