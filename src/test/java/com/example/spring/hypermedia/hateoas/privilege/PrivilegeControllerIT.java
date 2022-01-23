package com.example.spring.hypermedia.hateoas.privilege;

import com.example.spring.hypermedia.hateoas.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;

import static com.jayway.jsonpath.internal.JsonFormatter.prettyPrint;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@SpringBootTest
@AutoConfigureMockMvc
class PrivilegeControllerIT
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
    void getPrivileges() throws Exception
    {
        String result = mvc.perform(get("/privileges"))
                           .andDo(print())
                           .andExpect(header().string("Content-Type", MediaTypes.HAL_JSON_VALUE))
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

        this.testUtils.dump(result);
    }

    @Test
    void getPrivilege() throws Exception
    {
        String privilegeId = "4a052805-9ec4-4de6-a512-cd74d8a5c00c";
        String result = mvc.perform(get("/privileges/{id}", privilegeId))
                           .andDo(print())
                           .andExpect(header().string("Content-Type", MediaTypes.HAL_JSON_VALUE))
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

        this.testUtils.dump(result);
    }
}
