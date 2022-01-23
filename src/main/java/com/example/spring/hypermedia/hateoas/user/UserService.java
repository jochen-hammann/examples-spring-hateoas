package com.example.spring.hypermedia.hateoas.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private List<User> users;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    public UserService()
    {
        this.users = new ArrayList<>();
        this.users.add(new User("efc8457a-fef4-46b1-85f0-954c118c3864", "userOne", "User", "One",
                Arrays.asList("4a052805-9ec4-4de6-a512-cd74d8a5c00c", "847b9812-5cf1-40db-b5c7-c704c50d86f0")));
        this.users.add(new User("b209dcd3-6e2a-48f3-89b3-9a527a56bf30", "userTwo", "User", "Two",
                Arrays.asList("b643f0b0-3d58-4c82-9dd6-2f974375d2c6", "fd351f15-fb75-4c2c-8cfb-b709bc3318a7")));
    }

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    public Optional<User> getUser(String id)
    {
        return this.users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

}
