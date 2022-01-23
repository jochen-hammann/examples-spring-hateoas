package com.example.spring.hypermedia.hateoas.user;

import com.example.spring.hypermedia.hateoas.privilege.Privilege;
import com.example.spring.hypermedia.hateoas.privilege.PrivilegeController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserRepresentationModelAssembler implements SimpleRepresentationModelAssembler<User>
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    // -------------------- [Public Fields] --------------------

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Private Construction / Destruction] --------------------

    // -------------------- [Public Construction / Destruction] --------------------

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

    @Override
    public void addLinks(EntityModel<User> resource)
    {
        Link selfRel = linkTo(methodOn(UserController.class).getUser(resource.getContent().getId())).withSelfRel();
        Link allUsersRel = linkTo(methodOn(UserController.class).getUsers()).withRel("allUsers");
        Link privilegesRel = linkTo(methodOn(UserController.class).getPrivileges(resource.getContent().getId())).withRel("privileges");

        resource.add(selfRel);
        resource.add(allUsersRel);
        resource.add(privilegesRel);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<User>> resources)
    {
        Link link = linkTo(methodOn(UserController.class).getUsers()).withSelfRel();
        resources.add(link);
    }
}
