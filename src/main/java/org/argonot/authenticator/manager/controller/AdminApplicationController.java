package org.argonot.authenticator.manager.controller;

import org.argonot.authenticator.business.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * <b>AdminApplicationController</b> operate for HTTP transactions from the BO
 * for applications administration
 * 
 * @author Meidi
 *
 */
@Controller
@RequestMapping("/admin/applications")
public class AdminApplicationController {

    @Autowired
    private ApplicationService applicationService;

    /**
     * Presents all the applications in a tab
     * 
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView applications() {
        ModelAndView page = new ModelAndView("Applications");
        page.addObject("applications", applicationService.listApplications());
        return page;
    }
}
