package com.example.spring.hypermedia.hateoas.privilege;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PrivilegeRepresentationModelAssembler implements SimpleRepresentationModelAssembler<Privilege>
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
    public void addLinks(EntityModel<Privilege> resource)
    {
        Link selfRel = linkTo(methodOn(PrivilegeController.class).getPrivilege(resource.getContent().getId())).withSelfRel();
        Link allPrivilegesRel = linkTo(methodOn(PrivilegeController.class).getPrivileges()).withRel("allPrivileges");

        resource.add(selfRel);
        resource.add(allPrivilegesRel);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Privilege>> resources)
    {
        Link link = linkTo(methodOn(PrivilegeController.class).getPrivileges()).withSelfRel();
        resources.add(link);
    }
}
