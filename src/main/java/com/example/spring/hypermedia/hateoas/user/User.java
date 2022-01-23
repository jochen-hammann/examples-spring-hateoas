package com.example.spring.hypermedia.hateoas.user;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.http.MediaType;

import java.util.List;

// @formatter:off
@Schema(description = "A user.")
// @formatter:on
@Relation(itemRelation = "user", collectionRelation = "users")
public class User
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    // @formatter:off
    @Schema(description = "The user's ID.", format = "uuid")
    // @formatter:on
    private String id;

    // @formatter:off
    @Schema(description = "The user's login name.")
    // @formatter:on
    private String loginName;

    // @formatter:off
    @Schema(description = "The user's first name.")
    // @formatter:on
    private String firstName;

    // @formatter:off
    @Schema(description = "The user's last name.")
    // @formatter:on
    private String lastName;

    // @formatter:off
    @Schema(description = "The user's privileges.")
    // @formatter:on
    private List<String> privileges;

    // -------------------- [Public Fields] --------------------

    public static final String HAL_TYPE_VALUE = "application/vnd.user.v1.hal+json";
    public static final MediaType HAL_TYPE = MediaType.parseMediaType(HAL_TYPE_VALUE);

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    public User()
    {
    }

    public User(String id, String loginName, String firstName, String lastName, List<String> privileges)
    {
        this.id = id;
        this.loginName = loginName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.privileges = privileges;
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

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public List<String> getPrivileges()
    {
        return privileges;
    }

    public void setPrivileges(List<String> privileges)
    {
        this.privileges = privileges;
    }
}
