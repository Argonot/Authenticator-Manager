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

    private static final String AUTHORIZATION_LIST_PAGE_HTTP_ATTR = "Admin";

    private static final String AUTHORIZATION_LIST_HTTP_ATTR = "authorizations";

    private static final String UPDATE_OR_CREATE_ACTION_HTTP_ATTR = "action";

    private static final String ROLE_LIST_HTTP_ATTR = "roles";

    private static final String APPLICATION_LIST_HTTP_ATTR = "applications";

    private static final String USER_LIST_HTTP_ATTR = "users";

    private static final String AUTHORIZATION_HTTP_ATTR = "authorization";

    private static final String AUTHORIZATION_PROVIDED_HTTP_ATTR = "authorizationVO";

    private static final String AUTHORIZATION_PAGE_HTTP_ATTR = "Authorization";

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
        ModelAndView page = new ModelAndView(AUTHORIZATION_PAGE_HTTP_ATTR);
        page.addObject(AUTHORIZATION_PROVIDED_HTTP_ATTR, new AuthorizationVO());
        page.addObject(AUTHORIZATION_HTTP_ATTR, authorizationService.find(idAuth));
        page.addObject(USER_LIST_HTTP_ATTR, userService.listUsers());
        page.addObject(APPLICATION_LIST_HTTP_ATTR, applicationService.listApplications());
        page.addObject(ROLE_LIST_HTTP_ATTR, roleService.listRoles());
        page.addObject(UPDATE_OR_CREATE_ACTION_HTTP_ATTR, "update");
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
            @ModelAttribute(AUTHORIZATION_PROVIDED_HTTP_ATTR) @Valid AuthorizationVO authorizationVO,
            BindingResult bindingResult) {
        ModelAndView page = new ModelAndView(AUTHORIZATION_PAGE_HTTP_ATTR);
        if (!bindingResult.hasErrors()) {
            page.addObject(AUTHORIZATION_HTTP_ATTR,
                    authorizationService.update(mapper.map(authorizationVO, Authorization.class), idAuth));
            page.addObject(AUTHORIZATION_LIST_HTTP_ATTR, authorizationService.listAuthorizations());
            page.setViewName(AUTHORIZATION_LIST_PAGE_HTTP_ATTR);
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
        ModelAndView page = new ModelAndView(AUTHORIZATION_PAGE_HTTP_ATTR);
        page.addObject(AUTHORIZATION_PROVIDED_HTTP_ATTR, new AuthorizationVO());
        page.addObject(USER_LIST_HTTP_ATTR, userService.listUsers());
        page.addObject(APPLICATION_LIST_HTTP_ATTR, applicationService.listApplications());
        page.addObject(ROLE_LIST_HTTP_ATTR, roleService.listRoles());
        return page;
    }

    /**
     * Create new Authorization
     * 
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView authorisationCreate(@ModelAttribute(AUTHORIZATION_PROVIDED_HTTP_ATTR) @Valid AuthorizationVO authorizationVO,
            BindingResult bindingResult) {
        ModelAndView page = new ModelAndView(AUTHORIZATION_PAGE_HTTP_ATTR);
        if (!bindingResult.hasErrors()) {
            authorizationService.create(mapper.map(authorizationVO, Authorization.class));
            page.addObject(AUTHORIZATION_LIST_HTTP_ATTR, authorizationService.listAuthorizations());
            page.setViewName(AUTHORIZATION_LIST_PAGE_HTTP_ATTR);
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
        ModelAndView page = new ModelAndView(AUTHORIZATION_LIST_PAGE_HTTP_ATTR);
        authorizationService.removeAuthorization(idAuth);
        page.addObject(AUTHORIZATION_LIST_HTTP_ATTR, authorizationService.listAuthorizations());
        return page;
    }

}
