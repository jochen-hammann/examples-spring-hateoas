package com.example.spring.hypermedia.hateoas.affordances;

import com.example.spring.hypermedia.hateoas.user.User;
import com.example.spring.hypermedia.hateoas.user.UserController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AffordanceUserRepresentationModelAssembler implements SimpleRepresentationModelAssembler<User>
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
        // Caution: For affordances to work, the class 'HateoasApplication' requires the following annotation:
        //          @EnableHypermediaSupport(type = { EnableHypermediaSupport.HypermediaType.HAL, EnableHypermediaSupport.HypermediaType.HAL_FORMS })
        Link selfRel = linkTo(methodOn(UserController.class).getUser(resource.getContent().getId())).withSelfRel().andAffordance(
                afford(methodOn(UserController.class).postUser(null))).andAffordance(
                afford(methodOn(UserController.class).putUser(resource.getContent().getId(), null))).andAffordance(
                afford(methodOn(UserController.class).patchUser(resource.getContent().getId(), null))).andAffordance(
                afford(methodOn(UserController.class).deleteUser(resource.getContent().getId())));

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
