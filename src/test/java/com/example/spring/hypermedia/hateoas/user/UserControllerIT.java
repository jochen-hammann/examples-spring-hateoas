package com.example.spring.hypermedia.hateoas.user;

import com.example.spring.hypermedia.hateoas.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestUtils testUtils;

    // ============================== [Unit Tests] ==============================

    // -------------------- [Test Helper Classes] --------------------

    // -------------------- [Test Helper Methods] --------------------

    // -------------------- [Test Initialization] --------------------

    // -------------------- [Tests] --------------------

    @Test
    void getUsers() throws Exception
    {
        String result = mvc.perform(get("/users"))
                           .andDo(print())
                           .andExpect(header().string("Content-Type", MediaTypes.HAL_JSON_VALUE))
                           .andExpect(jsonPath("$._links", notNullValue()))
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

        this.testUtils.dump(result);
    }

    @Test
    void getUsersWrapped() throws Exception
    {
        String result = mvc.perform(get("/usersWrapped"))
                           .andDo(print())
                           .andExpect(header().string("Content-Type", MediaTypes.HAL_JSON_VALUE))
                           .andExpect(jsonPath("$._links", notNullValue()))
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

        this.testUtils.dump(result);
    }

    @Test
    void getUsersCollection() throws Exception
    {
        String result = mvc.perform(get("/usersCollection"))
                           .andDo(print())
                           .andExpect(header().string("Content-Type", MediaTypes.HAL_JSON_VALUE))
                           .andExpect(jsonPath("$._links", notNullValue()))
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

        this.testUtils.dump(result);
    }

    @Test
    void getUser() throws Exception
    {
        String userId = "efc8457a-fef4-46b1-85f0-954c118c3864";
        String result = mvc.perform(get("/users/{id}", userId))
                           .andDo(print())
                           .andExpect(header().string("Content-Type", MediaTypes.HAL_JSON_VALUE))
                           .andExpect(jsonPath("$._links", notNullValue()))
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

        this.testUtils.dump(result);
    }

    @Test
    void getPrivileges() throws Exception
    {
        String userId = "efc8457a-fef4-46b1-85f0-954c118c3864";
        String result = mvc.perform(get("/users/{id}/privileges", userId))
                           .andDo(print())
                           .andExpect(header().string("Content-Type", MediaTypes.HAL_JSON_VALUE))
                           .andExpect(jsonPath("$._links", notNullValue()))
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

        this.testUtils.dump(result);
    }

    @Test
    void getUsersWithVendorMediaType() throws Exception
    {
        String result = mvc.perform(get("/users/vendor/mediatype"))
                           .andDo(print())
                           .andExpect(header().string("Content-Type", User.HAL_TYPE_VALUE))
                           .andExpect(jsonPath("$._links", notNullValue()))
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

        this.testUtils.dump(result);
    }
}
