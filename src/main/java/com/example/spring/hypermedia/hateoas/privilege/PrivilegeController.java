package com.example.spring.hypermedia.hateoas.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PrivilegeController
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private PrivilegeService service;
    private PrivilegeRepresentationModelAssembler assembler;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    @Autowired
    public PrivilegeController(PrivilegeService service, PrivilegeRepresentationModelAssembler assembler)
    {
        this.service = service;
        this.assembler = assembler;
    }

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

    // Hint: To specify the response's Content-Type you can use the 'produces' parameter of the annotation @[Get,Post,...]Mapping or
    //       the contentType() method of the ResponseEntity instance. The contentType method overwrites the 'produces' parameter.

    @GetMapping(value = "/privileges")
    public ResponseEntity<CollectionModel<EntityModel<Privilege>>> getPrivileges()
    {
        List<Privilege> privileges = this.service.getPrivileges();
        CollectionModel<EntityModel<Privilege>> response = this.assembler.toCollectionModel(privileges);

        return ResponseEntity.ok().contentType(MediaTypes.HAL_JSON).body(response);
    }

    @GetMapping(value = "/privileges/{id}",
                produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Privilege> getPrivilege(@PathVariable String id)
    {
        Optional<Privilege> privilege = this.service.getPrivilege(id);

        // We assume, that the privilege exists.
        EntityModel<Privilege> response = this.assembler.toModel(privilege.get());

        return response;
    }
}
