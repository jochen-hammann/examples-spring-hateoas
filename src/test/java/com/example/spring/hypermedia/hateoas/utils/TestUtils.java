package com.example.spring.hypermedia.hateoas.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestUtils
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private ObjectMapper objectMapper;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    @Autowired
    public TestUtils(ObjectMapper objectMapper)
    {
        this.objectMapper = objectMapper;
    }

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

    public void dump(String jsonStr) throws JsonProcessingException
    {
        Object obj = this.objectMapper.readValue(jsonStr, Object.class);
        String prettyJsonStr = this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);

        System.out.println(prettyJsonStr);
    }

    public void dumpObj(Object obj) throws JsonProcessingException
    {
        String prettyJsonStr = this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);

        System.out.println(prettyJsonStr);
    }
}
