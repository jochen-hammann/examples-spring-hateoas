package com.example.spring.hypermedia.hateoas.haluser;

import com.example.spring.hypermedia.hateoas.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@SpringBootTest
@AutoConfigureMockMvc
class HalUserControllerIT
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
        String result = mvc.perform(get("/hal/users"))
                           .andDo(print())
                           .andExpect(header().string("Content-Type", MediaTypes.HAL_JSON_VALUE))
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

        this.testUtils.dump(result);
    }

    @Test
    void getUsersHalBuilder() throws Exception
    {
        String result = mvc.perform(get("/hal/usersHalBuilder"))
                           .andExpect(header().string("Content-Type", MediaTypes.HAL_JSON_VALUE))
                           .andDo(print())
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

        this.testUtils.dump(result);
    }

    @Test
    void getUsersAndPrivileges() throws Exception
    {
        String result = mvc.perform(get("/hal/usersAndPrivileges"))
                           .andExpect(header().string("Content-Type", MediaTypes.HAL_JSON_VALUE))
                           .andDo(print())
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

        this.testUtils.dump(result);
    }

    @Test
    void getUser() throws Exception
    {
        String userId = "efc8457a-fef4-46b1-85f0-954c118c3864";
        String result = mvc.perform(get("/hal/users/{id}", userId))
                           .andExpect(header().string("Content-Type", MediaTypes.HAL_JSON_VALUE))
                           .andDo(print())
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

        this.testUtils.dump(result);
    }

    @Test
    void getPrivileges() throws Exception
    {
        String userId = "efc8457a-fef4-46b1-85f0-954c118c3864";
        String result = mvc.perform(get("/hal/users/{id}/privileges", userId))
                           .andExpect(header().string("Content-Type", MediaTypes.HAL_JSON_VALUE))
                           .andDo(print())
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

        this.testUtils.dump(result);
    }
}
