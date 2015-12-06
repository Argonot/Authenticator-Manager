package org.argonot.authenticator.manager.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Application Value Object
 * 
 * @author Meidi
 *
 */
public class ApplicationVO {

    private static final int AUID_SIZE = 3;

    /**
     * AUID : Application Unique ID
     */
    @NotNull
    @NotEmpty
    @Length(min = AUID_SIZE, max = AUID_SIZE)
    private String id;

    /**
     * Application name
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
