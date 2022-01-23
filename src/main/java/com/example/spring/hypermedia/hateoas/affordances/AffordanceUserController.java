package com.example.spring.hypermedia.hateoas.affordances;

import com.example.spring.hypermedia.hateoas.privilege.Privilege;
import com.example.spring.hypermedia.hateoas.privilege.PrivilegeRepresentationModelAssembler;
import com.example.spring.hypermedia.hateoas.privilege.PrivilegeService;
import com.example.spring.hypermedia.hateoas.user.User;
import com.example.spring.hypermedia.hateoas.user.UserRepresentationModelAssembler;
import com.example.spring.hypermedia.hateoas.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AffordanceUserController
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private UserService service;
    private AffordanceUserRepresentationModelAssembler assembler;

    private PrivilegeService privilegeService;
    private PrivilegeRepresentationModelAssembler privilegeAssembler;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    @Autowired
    public AffordanceUserController(UserService service, AffordanceUserRepresentationModelAssembler assembler, PrivilegeService privilegeService,
            PrivilegeRepresentationModelAssembler privilegeAssembler)
    {
        this.service = service;
        this.assembler = assembler;

        this.privilegeService = privilegeService;
        this.privilegeAssembler = privilegeAssembler;
    }

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

    // Hint: To specify the response's Content-Type you can use the 'produces' parameter of the annotation @[Get,Post,...]Mapping or
    //       the contentType() method of the ResponseEntity instance. The contentType method overwrites the 'produces' parameter.

    @GetMapping(value = "/affordances/users",
                produces = MediaTypes.HAL_FORMS_JSON_VALUE)
    public CollectionModel<EntityModel<User>> getUsers()
    {
        List<User> users = this.service.getUsers();
        CollectionModel<EntityModel<User>> response = this.assembler.toCollectionModel(users);

        return response;
    }

    @GetMapping(value = "/affordances/users/{id}",
                produces = MediaTypes.HAL_FORMS_JSON_VALUE)
    public EntityModel<User> getUser(@PathVariable String id)
    {
        // We assume, that the user exists.
        User user = this.service.getUser(id).get();

        EntityModel<User> response = this.assembler.toModel(user);

        return response;
    }

    @PostMapping(value = "/affordances/users",
                 produces = MediaTypes.HAL_FORMS_JSON_VALUE)
    public CollectionModel<User> postUser(@RequestBody User user)
    {
        // The example does not require an implementation of this method.

        return null;
    }

    @PutMapping(value = "/affordances/users/{id}",
                produces = MediaTypes.HAL_FORMS_JSON_VALUE)
    public CollectionModel<User> putUser(@PathVariable String id, @RequestBody User user)
    {
        // The example does not require an implementation of this method.

        return null;
    }

    @PatchMapping(value = "/affordances/users/{id}",
                  produces = MediaTypes.HAL_FORMS_JSON_VALUE)
    public CollectionModel<User> patchUser(@PathVariable String id, @RequestBody User user)
    {
        // The example does not require an implementation of this method.

        return null;
    }

    @DeleteMapping(value = "/affordances/users/{id}",
                   produces = MediaTypes.HAL_FORMS_JSON_VALUE)
    public CollectionModel<User> deleteUser(@PathVariable String id)
    {
        // The example does not require an implementation of this method.

        return null;
    }
}
