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

    private static final String UPDATE_OR_CREATE_ACTION_HTTP_ATTR = "action";

    private static final String ROLE_HTTP_ATTR = "role";

    private static final String ROLE_PROVIDED_HTTP_ATTR = "roleVO";

    private static final String ROLE_PAGE_HTTP_ATTR = "Role";

    private static final String ROLE_LIST_HTTP_ATTR = "roles";

    private static final String ROLE_LIST_PAGE_HTTP_ATTR = "Roles";

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
        ModelAndView page = new ModelAndView(ROLE_LIST_PAGE_HTTP_ATTR);
        page.addObject(ROLE_LIST_HTTP_ATTR, roleService.listRoles());
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
        ModelAndView page = new ModelAndView(ROLE_PAGE_HTTP_ATTR);
        page.addObject(ROLE_PROVIDED_HTTP_ATTR, new RoleVO());
        page.addObject(ROLE_HTTP_ATTR, roleService.find(ruid));
        page.addObject(UPDATE_OR_CREATE_ACTION_HTTP_ATTR, "update");
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
    public ModelAndView roleUpdate(@ModelAttribute(ROLE_PROVIDED_HTTP_ATTR) @Valid RoleVO roleVO,
            BindingResult bindingResult) {
        ModelAndView page = new ModelAndView(ROLE_PAGE_HTTP_ATTR);
        if (!bindingResult.hasErrors()) {
            page.addObject(ROLE_HTTP_ATTR, roleService.update(mapper.map(roleVO, Role.class)));
            page.addObject(ROLE_LIST_HTTP_ATTR, roleService.listRoles());
            page.setViewName(ROLE_LIST_PAGE_HTTP_ATTR);
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
        ModelAndView page = new ModelAndView(ROLE_PAGE_HTTP_ATTR);
        page.addObject(ROLE_PROVIDED_HTTP_ATTR, new RoleVO());
        return page;
    }

    /**
     * Create new Role
     * 
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView roleCreate(@ModelAttribute(ROLE_PROVIDED_HTTP_ATTR) @Valid RoleVO roleVO,
            BindingResult bindingResult) {
        ModelAndView page = new ModelAndView(ROLE_PAGE_HTTP_ATTR);
        if (!bindingResult.hasErrors()) {
            roleService.create(mapper.map(roleVO, Role.class));
            page.addObject(ROLE_LIST_HTTP_ATTR, roleService.listRoles());
            page.setViewName(ROLE_LIST_PAGE_HTTP_ATTR);
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
        ModelAndView page = new ModelAndView(ROLE_LIST_PAGE_HTTP_ATTR);
        roleService.removeRole(ruid);
        page.addObject(ROLE_LIST_HTTP_ATTR, roleService.listRoles());
        return page;
    }

}
