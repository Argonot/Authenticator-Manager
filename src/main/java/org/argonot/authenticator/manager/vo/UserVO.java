package org.argonot.authenticator.manager.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * User Value Object
 * 
 * @author Meidi
 *
 */
public class UserVO {

    /**
     * User name provided
     */
    @NotNull
    @NotEmpty
    private String name;

    /**
     * User surname provided
     */
    @NotNull
    @NotEmpty
    private String surname;

    /**
     * User email provided
     */
    @NotNull
    @NotEmpty
    @Email
    private String email;

    /**
     * User password provided
     */
    @NotNull
    @NotEmpty
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
