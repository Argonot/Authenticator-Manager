package org.argonot.authenticator.manager.controller;

import org.argonot.authenticator.business.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * <b>AdminRoleController</b> operate for HTTP transactions from the BO for
 * roles administration
 * 
 * @author Meidi
 *
 */
@Controller
@RequestMapping("/admin/roles")
public class AdminRoleController {

    @Autowired
    private RoleService roleService;

    /**
     * Presents all the roles in a tab
     * 
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView roles() {
        ModelAndView page = new ModelAndView("Roles");
        page.addObject("roles", roleService.listRoles());
        return page;
    }

}
