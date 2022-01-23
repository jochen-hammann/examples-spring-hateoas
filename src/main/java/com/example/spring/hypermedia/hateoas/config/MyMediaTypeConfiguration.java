package com.example.spring.hypermedia.hateoas.config;

import com.example.spring.hypermedia.hateoas.user.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.HypermediaMappingInformation;
import org.springframework.hateoas.mediatype.MessageResolver;
import org.springframework.hateoas.mediatype.hal.CurieProvider;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.hateoas.server.core.EvoInflectorLinkRelationProvider;
import org.springframework.http.MediaType;

import java.util.List;

// Hint: Implementation taken from https://github.com/spring-projects/spring-hateoas/issues/1253#issuecomment-608973523.

// Caution: Requires the Evo Inflector to be added to the POM.
//          <dependency>
//              <groupId>org.atteo</groupId>
//              <artifactId>evo-inflector</artifactId>
//              <version>${evo-inflector.version}</version>
//          </dependency>

@Configuration
public class MyMediaTypeConfiguration implements HypermediaMappingInformation
{
    // ============================== [Getter/Setter] ==============================

    // -------------------- [Public Getter/Setter] --------------------

    @Override
    public List<MediaType> getMediaTypes()
    {
        return List.of(User.HAL_TYPE);
    }

    @Override
    public ObjectMapper configureObjectMapper(ObjectMapper mapper)
    {
        mapper.registerModule(getJacksonModule());
        mapper.setHandlerInstantiator(new Jackson2HalModule.HalHandlerInstantiator(new EvoInflectorLinkRelationProvider(), CurieProvider.NONE,
                MessageResolver.DEFAULTS_ONLY));

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // mapper.enable(SerializationFeature.INDENT_OUTPUT);

        return mapper;
    }

    @Override
    public Module getJacksonModule()
    {
        return new Jackson2HalModule();
    }
}
