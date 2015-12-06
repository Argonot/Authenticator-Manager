package org.argonot.authenticator.manager.controller;

import javax.validation.Valid;

import org.argonot.authenticator.business.entity.Role;
import org.argonot.authenticator.business.service.RoleService;
import org.argonot.authenticator.manager.vo.RoleVO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private Mapper mapper;

    /**
     * Presents all the roles in a tab
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView roles() {
        ModelAndView page = new ModelAndView("Roles");
        page.addObject("roles", roleService.listRoles());
        return page;
    }

    /**
     * Display a role
     * 
     * @param ruid
     *            : role unique id
     * @return
     */
    @RequestMapping(value = "/{ruid}/update", method = RequestMethod.GET)
    public ModelAndView role(@PathVariable String ruid) {
        ModelAndView page = new ModelAndView("Role");
        page.addObject("roleVO", new RoleVO());
        page.addObject("role", roleService.find(ruid));
        page.addObject("action", "update");
        return page;
    }

    /**
     * Update a role
     * 
     * @param ruid
     *            : role unique id
     * @return
     */
    @RequestMapping(value = "/{ruid}/update", method = RequestMethod.POST)
    public ModelAndView roleUpdate(@ModelAttribute("roleVO") @Valid RoleVO roleVO,
            BindingResult bindingResult) {
        ModelAndView page = new ModelAndView("Role");
        if (!bindingResult.hasErrors()) {
            page.addObject("role", roleService.update(mapper.map(roleVO, Role.class)));
            page.addObject("roles", roleService.listRoles());
            page.setViewName("Roles");
        }
        return page;
    }

    /**
     * Role creation route
     * 
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView role() {
        ModelAndView page = new ModelAndView("Role");
        page.addObject("roleVO", new RoleVO());
        return page;
    }

    /**
     * Create new Role
     * 
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView roleCreate(@ModelAttribute("roleVO") @Valid RoleVO roleVO,
            BindingResult bindingResult) {
        ModelAndView page = new ModelAndView("Role");
        if (!bindingResult.hasErrors()) {
            roleService.create(mapper.map(roleVO, Role.class));
            page.addObject("roles", roleService.listRoles());
            page.setViewName("Roles");
        }
        return page;
    }

    /**
     * Delete a role permanently
     * 
     * @param ruid
     *            : role unique id
     * @return
     */
    @RequestMapping(value = "/{ruid}/delete", method = RequestMethod.GET)
    public ModelAndView roleDelete(@PathVariable String ruid) {
        ModelAndView page = new ModelAndView("Roles");
        roleService.removeRole(ruid);
        page.addObject("roles", roleService.listRoles());
        return page;
    }

}
