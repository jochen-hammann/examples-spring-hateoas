package com.example.spring.hypermedia.hateoas.root;

import com.example.spring.hypermedia.hateoas.privilege.PrivilegeController;
import com.example.spring.hypermedia.hateoas.privilege.PrivilegeRepresentationModelAssembler;
import com.example.spring.hypermedia.hateoas.privilege.PrivilegeService;
import com.example.spring.hypermedia.hateoas.user.UserController;
import com.example.spring.hypermedia.hateoas.user.UserRepresentationModelAssembler;
import com.example.spring.hypermedia.hateoas.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RootController
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
    public RootController(UserService service, UserRepresentationModelAssembler assembler, PrivilegeService privilegeService,
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

    @GetMapping(value = "/",
                produces = MediaTypes.HAL_JSON_VALUE)
    public RepresentationModel getRoot() throws NoSuchMethodException
    {
        RepresentationModel model = new RepresentationModel();

        model.add(linkTo(methodOn(RootController.class).getRoot()).withSelfRel());
        model.add(linkTo(methodOn(UserController.class).getUsers()).withRel("users"));
        model.add(Link.of(UriTemplate.of(linkTo(methodOn(UserController.class).getUser("")).toString() + "{id}"), "user"));
        model.add(linkTo(methodOn(PrivilegeController.class).getPrivileges()).withRel("privileges"));

        return model;
    }
}
