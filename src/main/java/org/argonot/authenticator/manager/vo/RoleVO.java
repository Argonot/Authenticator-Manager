package org.argonot.authenticator.manager.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Role Value Object
 * 
 * @author Meidi
 *
 */
public class RoleVO {

    /**
     * RUID : Role Unique ID
     */
    @NotNull
    @NotEmpty
    @Length(min = 3, max = 3)
    private String id;

    /**
     * Role name
     */
    @NotNull
    @NotEmpty
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
