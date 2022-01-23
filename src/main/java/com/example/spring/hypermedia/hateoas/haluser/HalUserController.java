package com.example.spring.hypermedia.hateoas.haluser;

import com.example.spring.hypermedia.hateoas.privilege.Privilege;
import com.example.spring.hypermedia.hateoas.privilege.PrivilegeController;
import com.example.spring.hypermedia.hateoas.privilege.PrivilegeRepresentationModelAssembler;
import com.example.spring.hypermedia.hateoas.privilege.PrivilegeService;
import com.example.spring.hypermedia.hateoas.user.User;
import com.example.spring.hypermedia.hateoas.user.UserController;
import com.example.spring.hypermedia.hateoas.user.UserRepresentationModelAssembler;
import com.example.spring.hypermedia.hateoas.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class HalUserController
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
    public HalUserController(UserService service, UserRepresentationModelAssembler assembler, PrivilegeService privilegeService,
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

    private RepresentationModel<EntityModel<User>> buildUser(User user)
    {
        List<RepresentationModel<EntityModel<Privilege>>> privilegesOfUser = user.getPrivileges()
                                                                                 .stream()
                                                                                 .map(this::buildPrivilege)
                                                                                 .collect(Collectors.toList());

        Link selfRel = linkTo(methodOn(HalUserController.class).getUser(user.getId())).withSelfRel();
        Link allUsersRel = linkTo(methodOn(HalUserController.class).getUsers()).withRel("allUsers");
        Link privilegesRel = linkTo(methodOn(HalUserController.class).getPrivileges(user.getId())).withRel("privileges");

        RepresentationModel<EntityModel<User>> halUser = HalModelBuilder.halModelOf(user)
                                                                        .link(selfRel)
                                                                        .link(allUsersRel)
                                                                        .link(privilegesRel)
                                                                        .embed(privilegesOfUser)
                                                                        .build();

        return halUser;
    }

    private RepresentationModel<EntityModel<Privilege>> buildPrivilege(String privilegeId)
    {
        // We assume that the privilege exists.
        Privilege privilege = this.privilegeService.getPrivilege(privilegeId).get();

        return this.buildPrivilege(privilege);
    }

    private RepresentationModel<EntityModel<Privilege>> buildPrivilege(Privilege privilege)
    {
        Link selfRel = linkTo(methodOn(PrivilegeController.class).getPrivilege(privilege.getId())).withSelfRel();

        RepresentationModel<EntityModel<Privilege>> halPrivilege = HalModelBuilder.halModelOf(privilege).link(selfRel).build();

        return halPrivilege;
    }

    // -------------------- [Public Methods] --------------------

    // Hint: To specify the response's 'Content-Type' you can use the 'produces' parameter of '@[Get,Post,...]Mapping' or the 'contentType()' method
    //       of 'ResponseEntity'.

    @GetMapping(value = "/hal/users")
    public ResponseEntity<CollectionModel<RepresentationModel<EntityModel<User>>>> getUsers()
    {
        List<User> users = this.service.getUsers();
        List<RepresentationModel<EntityModel<User>>> userModels = users.stream().map(this::buildUser).collect(Collectors.toList());

        Link selfRel = linkTo(methodOn(HalUserController.class).getUsers()).withSelfRel();

        CollectionModel<RepresentationModel<EntityModel<User>>> collectionModel = CollectionModel.of(userModels);
        collectionModel.add(selfRel);

        return ResponseEntity.ok().contentType(MediaTypes.HAL_JSON).body(collectionModel);
    }

    @GetMapping(value = "/hal/usersHalBuilder",
                produces = MediaTypes.HAL_JSON_VALUE)
    public RepresentationModel<EntityModel<User>> getUsersHalBuilder()
    {
        List<User> users = this.service.getUsers();
        List<RepresentationModel<EntityModel<User>>> userModels = users.stream().map(this::buildUser).collect(Collectors.toList());

        Link selfRel = linkTo(methodOn(HalUserController.class).getUsers()).withSelfRel();

        return HalModelBuilder.halModel().link(selfRel).embed(userModels).build();
    }

    @GetMapping(value = "/hal/usersAndPrivileges",
                produces = MediaTypes.HAL_JSON_VALUE)
    public RepresentationModel<CollectionModel<RepresentationModel<EntityModel<User>>>> getUsersAndPrivileges()
    {
        List<User> users = this.service.getUsers();
        List<RepresentationModel<EntityModel<User>>> userModels = users.stream().map(this::buildUser).collect(Collectors.toList());

        List<Privilege> privileges = this.privilegeService.getPrivileges();
        List<RepresentationModel<EntityModel<Privilege>>> privilegeModels = privileges.stream()
                                                                                      .map(this::buildPrivilege)
                                                                                      .collect(Collectors.toList());

        Link selfRel = linkTo(methodOn(HalUserController.class).getUsers()).withSelfRel();

        return HalModelBuilder.halModel().link(selfRel).embed(userModels).embed(privilegeModels).build();
    }

    @GetMapping(value = "/hal/users/{id}",
                produces = MediaTypes.HAL_JSON_VALUE)
    public RepresentationModel<EntityModel<User>> getUser(@PathVariable String id)
    {
        // We assume, that the user exists.
        User user = this.service.getUser(id).get();

        return this.buildUser(user);
    }

    @GetMapping(value = "/hal/users/{id}/privileges",
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
}
