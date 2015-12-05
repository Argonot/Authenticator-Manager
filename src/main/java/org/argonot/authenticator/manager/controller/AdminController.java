package org.argonot.authenticator.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.argonot.authenticator.business.service.AuthorizationService;
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
