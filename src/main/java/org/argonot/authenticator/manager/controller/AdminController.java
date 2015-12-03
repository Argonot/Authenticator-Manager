package org.argonot.authenticator.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.argonot.authenticator.business.service.ApplicationService;
import org.argonot.authenticator.business.service.AuthorizationService;
import org.argonot.authenticator.business.service.RoleService;
import org.argonot.authenticator.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * <b>AdminController</b> operate for HTTP transactions from the BO
 * @author Meidi
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private AuthorizationService authorizationService;
    
    @Autowired
    private ApplicationService applicationService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    /**
     * Administration home page<br/>
     * Presents all the authorizations
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView authorizations() {
        ModelAndView page = new ModelAndView("Admin");
        page.addObject("authorizations", authorizationService.listAuthorizations());
        return page;
    }
    
    /**
     * Presents all the applications in a tab
     * @return
     */
    @RequestMapping(value = "/applications", method = RequestMethod.GET)
    public ModelAndView applications() {
        ModelAndView page = new ModelAndView("Applications");
        page.addObject("applications", applicationService.listApplications());
        return page;
    }
    
    /**
     * Presents all the users in a tab
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView users() {
        ModelAndView page = new ModelAndView("Users");
        page.addObject("users", userService.listUsers());
        return page;
    }
    
    /**
     * Presents all the roles in a tab
     * @return
     */
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ModelAndView roles() {
        ModelAndView page = new ModelAndView("Roles");
        page.addObject("roles", roleService.listRoles());
        return page;
    }
    
    /**
     * Close the user session
     * @return
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout (HttpServletRequest request, HttpServletResponse response) {
        ModelAndView page = new ModelAndView("Index");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return page;
    }

}
