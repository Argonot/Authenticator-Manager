package org.argonot.authenticator.manager.controller;

import javax.validation.Valid;

import org.argonot.authenticator.business.entity.Authorization;
import org.argonot.authenticator.business.service.ApplicationService;
import org.argonot.authenticator.business.service.AuthorizationService;
import org.argonot.authenticator.business.service.RoleService;
import org.argonot.authenticator.business.service.UserService;
import org.argonot.authenticator.manager.vo.AuthorizationVO;
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
 * <b>AdminAuthorizationController</b> operate for HTTP transactions from the BO
 * for authorizations administration
 * 
 * @author Meidi
 *
 */
@Controller
@RequestMapping("/admin/authorizations")
public class AdminAuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private Mapper mapper;

    /**
     * Display an authorization
     * 
     * @param idAuth
     *            : authorization unique id
     * @return
     */
    @RequestMapping(value = "/{idAuth}/update", method = RequestMethod.GET)
    public ModelAndView authorization(@PathVariable long idAuth) {
        ModelAndView page = new ModelAndView("Authorization");
        page.addObject("authorizationVO", new AuthorizationVO());
        page.addObject("authorization", authorizationService.find(idAuth));
        page.addObject("users", userService.listUsers());
        page.addObject("applications", applicationService.listApplications());
        page.addObject("roles", roleService.listRoles());
        page.addObject("action", "update");
        return page;
    }

    /**
     * Update an authorization
     * 
     * @param idAuth
     *            : authorization unique id
     * @return
     */
    @RequestMapping(value = "/{idAuth}/update", method = RequestMethod.POST)
    public ModelAndView authorizationUpdate(@PathVariable long idAuth,
            @ModelAttribute("authorizationVO") @Valid AuthorizationVO authorizationVO,
            BindingResult bindingResult) {
        ModelAndView page = new ModelAndView("Authorization");
        if (!bindingResult.hasErrors()) {
            page.addObject("authorization",
                    authorizationService.update(mapper.map(authorizationVO, Authorization.class), idAuth));
            page.addObject("authorizations", authorizationService.listAuthorizations());
            page.setViewName("Admin");
        }
        return page;
    }

    /**
     * Authorization creation route
     * 
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView authorization() {
        ModelAndView page = new ModelAndView("Authorization");
        page.addObject("authorizationVO", new AuthorizationVO());
        page.addObject("users", userService.listUsers());
        page.addObject("applications", applicationService.listApplications());
        page.addObject("roles", roleService.listRoles());
        return page;
    }

    /**
     * Create new Authorization
     * 
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView authorisationCreate(@ModelAttribute("authorizationVO") @Valid AuthorizationVO authorizationVO,
            BindingResult bindingResult) {
        ModelAndView page = new ModelAndView("Authorization");
        if (!bindingResult.hasErrors()) {
            authorizationService.create(mapper.map(authorizationVO, Authorization.class));
            page.addObject("authorizations", authorizationService.listAuthorizations());
            page.setViewName("Admin");
        }
        return page;
    }

    /**
     * Delete an authorization permanently
     * 
     * @param idAuth
     *            : authorization unique id
     * @return
     */
    @RequestMapping(value = "/{idAuth}/delete", method = RequestMethod.GET)
    public ModelAndView authorisationDelete(@PathVariable long idAuth) {
        ModelAndView page = new ModelAndView("Admin");
        authorizationService.removeAuthorization(idAuth);
        page.addObject("authorizations", authorizationService.listAuthorizations());
        return page;
    }

}
