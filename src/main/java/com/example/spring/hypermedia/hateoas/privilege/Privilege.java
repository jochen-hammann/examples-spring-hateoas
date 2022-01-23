package com.example.spring.hypermedia.hateoas.privilege;

public class Privilege
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private String id;
    private String name;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    public Privilege()
    {
    }

    public Privilege(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
