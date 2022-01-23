package com.example.spring.hypermedia.hateoas.user;

import com.example.spring.hypermedia.hateoas.privilege.Privilege;
import com.example.spring.hypermedia.hateoas.privilege.PrivilegeRepresentationModelAssembler;
import com.example.spring.hypermedia.hateoas.privilege.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private UserService service;
    private UserRepresentationModelAssembler assembler;

    private PrivilegeService privilegeService;
    private PrivilegeRepresentationModelAssembler privilegeAssembler;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    @Autowired
    public UserController(UserService service, UserRepresentationModelAssembler assembler, PrivilegeService privilegeService,
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

    @GetMapping(value = "/users")
    public ResponseEntity<CollectionModel<EntityModel<User>>> getUsers()
    {
        List<User> users = this.service.getUsers();
        CollectionModel<EntityModel<User>> response = this.assembler.toCollectionModel(users);

        return ResponseEntity.ok().contentType(MediaTypes.HAL_JSON).body(response);
    }

    @GetMapping(value = "/usersWrapped",
                produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<User>> getUsersWrapped()
    {
        List<User> users = this.service.getUsers();
        Link selfRel = linkTo(methodOn(UserController.class).getUsers()).withSelfRel();

        CollectionModel<EntityModel<User>> response = CollectionModel.wrap(users);
        response.add(selfRel);

        Iterator<EntityModel<User>> iter = response.iterator();
        while (iter.hasNext())
            this.assembler.addLinks(iter.next());

        return response;
    }

    @GetMapping(value = "/usersCollection",
                produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<User> getUsersCollection()
    {
        List<User> users = this.service.getUsers();
        Link selfRel = linkTo(methodOn(UserController.class).getUsers()).withSelfRel();

        CollectionModel<User> response = CollectionModel.of(users);
        response.add(selfRel);

        // Caution: Links cannot be added to the users, because they are not wrapped by an EntityModel.
        //          If we want to use this approach, we have a user extends EntityModel<User>. But then we must specify two models
        //          for GET (extends EntityModel<User>) and PUT/POST/PATCH (does not extend EntityModel<User>).

        return response;
    }

    @GetMapping(value = "/users/{id}",
                produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<User> getUser(@PathVariable String id)
    {
        // We assume, that the user exists.
        User user = this.service.getUser(id).get();

        EntityModel<User> response = this.assembler.toModel(user);

        return response;
    }

    @GetMapping(value = "/users/{id}/privileges",
                produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Privilege>> getPrivileges(@PathVariable String id)
    {
        // We assume, that the user exists.
        User user = this.service.getUser(id).get();

        List<String> privilegesOfUser = user.getPrivileges();

        List<Privilege> privileges = privilegesOfUser.stream()
                                                     .map(this.privilegeService::getPrivilege)
                                                     .map(Optional::get)
                                                     .collect(Collectors.toList());

        CollectionModel<EntityModel<Privilege>> response = this.privilegeAssembler.toCollectionModel(privileges);

        return response;
    }

    @GetMapping(value = "/users/vendor/mediatype")
    public ResponseEntity<CollectionModel<EntityModel<User>>> getUsersWithVendorMediaType()
    {
        List<User> users = this.service.getUsers();
        CollectionModel<EntityModel<User>> response = this.assembler.toCollectionModel(users);

        return ResponseEntity.ok().contentType(User.HAL_TYPE).body(response);
    }

    @PostMapping(value = "/users",
                 produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<User> postUser(@RequestBody User user)
    {
        // The example does not require an implementation of this method.

        return null;
    }

    @PutMapping(value = "/users/{id}",
                produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<User> putUser(@PathVariable String id, @RequestBody User user)
    {
        // The example does not require an implementation of this method.

        return null;
    }

    @PatchMapping(value = "/users/{id}",
                  produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<User> patchUser(@PathVariable String id, @RequestBody User user)
    {
        // The example does not require an implementation of this method.

        return null;
    }

    @DeleteMapping(value = "/users/{id}",
                   produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<User> deleteUser(@PathVariable String id)
    {
        // The example does not require an implementation of this method.

        return null;
    }
}
