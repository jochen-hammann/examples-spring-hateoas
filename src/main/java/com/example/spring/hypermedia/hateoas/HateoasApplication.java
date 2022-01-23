package com.example.spring.hypermedia.hateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;

// Caution: Affordances require HAL_FORMS. If HAL_FORMS is specified beside HAL, the response's 'Content-Type' will be
//          'application/prs.hal-forms+json' by default. If the Content-Type 'application/hal+json' should be returned, it has to be specified
//          by the 'produces' parameter of the annotation @[Get,Post,...]Mapping or the contentType() method of the ResponseEntity instance.

// Hint: The specification of HAL within the annotation @EnableHypermediaSupport is optional an may be omitted.

@SpringBootApplication
@EnableHypermediaSupport(type = { EnableHypermediaSupport.HypermediaType.HAL, EnableHypermediaSupport.HypermediaType.HAL_FORMS })
public class HateoasApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(HateoasApplication.class, args);
    }
}
