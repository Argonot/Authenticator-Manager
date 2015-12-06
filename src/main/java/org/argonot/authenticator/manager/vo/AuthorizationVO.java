package org.argonot.authenticator.manager.vo;

import javax.validation.constraints.NotNull;

import org.argonot.authenticator.business.entity.Application;
import org.argonot.authenticator.business.entity.Role;
import org.argonot.authenticator.business.entity.User;

/**
 * Authorization Value Object
 * 
 * @author Meidi
 *
 */
public class AuthorizationVO {

    /**
     * Provided user authorized
     */
    @NotNull
    private User user;

    /**
     * Provided application authorized for user
     */
    @NotNull
    private Application app;

    /**
     * Provided role authorized for user on application
     */
    @NotNull
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
