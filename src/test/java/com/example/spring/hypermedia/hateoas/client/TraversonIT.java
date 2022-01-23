package com.example.spring.hypermedia.hateoas.client;

import com.example.spring.hypermedia.hateoas.user.User;
import com.example.spring.hypermedia.hateoas.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.client.Hop.rel;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TraversonIT
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    @LocalServerPort
    private long localServerPort;

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
        String baseUrl = "http://localhost:" + localServerPort;
        Traverson traverson = new Traverson(URI.create(baseUrl), MediaTypes.HAL_JSON);

        List<EntityModel<User>> users = traverson.follow("users").toObject("$._embedded.users");

        this.testUtils.dumpObj(users);
    }

    @Test
    void getUsers_CollectionModel() throws Exception
    {
        String baseUrl = "http://localhost:" + localServerPort;
        Traverson traverson = new Traverson(URI.create(baseUrl), MediaTypes.HAL_JSON);

        CollectionModel<EntityModel<User>> users = traverson.follow("users").toObject(new ParameterizedTypeReference<>()
        {
        });

        this.testUtils.dumpObj(users);
    }

    @Test
    void getUser() throws Exception
    {
        String baseUrl = "http://localhost:" + localServerPort;
        Traverson traverson = new Traverson(URI.create(baseUrl), MediaTypes.HAL_JSON);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", "efc8457a-fef4-46b1-85f0-954c118c3864");

        User user = traverson.follow("user").withTemplateParameters(parameters).toObject(User.class);

        this.testUtils.dumpObj(user);
    }

    @Test
    void getUser_EntityModel() throws Exception
    {
        String baseUrl = "http://localhost:" + localServerPort;
        Traverson traverson = new Traverson(URI.create(baseUrl), MediaTypes.HAL_JSON);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", "efc8457a-fef4-46b1-85f0-954c118c3864");

        EntityModel<User> user = traverson.follow("user").withTemplateParameters(parameters).toObject(new ParameterizedTypeReference<>()
        {
        });

        this.testUtils.dumpObj(user);
    }

    @Test
    void getUser_Hop() throws Exception
    {
        String baseUrl = "http://localhost:" + localServerPort;
        Traverson traverson = new Traverson(URI.create(baseUrl), MediaTypes.HAL_JSON);

        User user = traverson.follow(rel("user").withParameter("id", "efc8457a-fef4-46b1-85f0-954c118c3864")).toObject(User.class);

        this.testUtils.dumpObj(user);
    }

    @Test
    void getUser_Hop_EntityModel() throws Exception
    {
        String baseUrl = "http://localhost:" + localServerPort;
        Traverson traverson = new Traverson(URI.create(baseUrl), MediaTypes.HAL_JSON);

        EntityModel<User> user = traverson.follow(rel("user").withParameter("id", "efc8457a-fef4-46b1-85f0-954c118c3864"))
                                          .toObject(new ParameterizedTypeReference<>()
                                          {
                                          });

        this.testUtils.dumpObj(user);
    }

    @Test
    void getAllUsers_FirstUser() throws Exception
    {
        String baseUrl = "http://localhost:" + localServerPort;
        Traverson traverson = new Traverson(URI.create(baseUrl), MediaTypes.HAL_JSON);

        User user = traverson.follow("users").follow("$._embedded.users[0]._links.self.href").toObject(User.class);

        this.testUtils.dumpObj(user);

        user = traverson.follow("users", "$._embedded.users[0]._links.self.href").toObject(User.class);

        this.testUtils.dumpObj(user);
    }
}
