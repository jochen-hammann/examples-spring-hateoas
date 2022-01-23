package com.example.spring.hypermedia.hateoas.privilege;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PrivilegeService
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private List<Privilege> privileges;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    public PrivilegeService()
    {
        this.privileges = new ArrayList<>();
        this.privileges.add(new Privilege("4a052805-9ec4-4de6-a512-cd74d8a5c00c", "Privilege 1"));
        this.privileges.add(new Privilege("847b9812-5cf1-40db-b5c7-c704c50d86f0", "Privilege 2"));
        this.privileges.add(new Privilege("b643f0b0-3d58-4c82-9dd6-2f974375d2c6", "Privilege 3"));
        this.privileges.add(new Privilege("fd351f15-fb75-4c2c-8cfb-b709bc3318a7", "Privilege 4"));
    }

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    public List<Privilege> getPrivileges()
    {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges)
    {
        this.privileges = privileges;
    }

    public Optional<Privilege> getPrivilege(String id)
    {
        return this.privileges.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

}
