package org.argonot.authenticator.manager.controller;

import javax.validation.Valid;

import org.argonot.authenticator.business.entity.Application;
import org.argonot.authenticator.business.service.ApplicationService;
import org.argonot.authenticator.manager.vo.ApplicationVO;
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

    @Autowired
    private Mapper mapper;

    /**
     * Presents all the applications in a tab
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView applications() {
        ModelAndView page = new ModelAndView("Applications");
        page.addObject("applications", applicationService.listApplications());
        return page;
    }

    /**
     * Display an application
     * 
     * @param auid
     *            : application unique id
     * @return
     */
    @RequestMapping(value = "/{auid}/update", method = RequestMethod.GET)
    public ModelAndView application(@PathVariable String auid) {
        ModelAndView page = new ModelAndView("Application");
        page.addObject("applicationVO", new ApplicationVO());
        page.addObject("application", applicationService.find(auid));
        page.addObject("action", "update");
        return page;
    }

    /**
     * Update an application
     * 
     * @param auid
     *            : application unique id
     * @return
     */
    @RequestMapping(value = "/{auid}/update", method = RequestMethod.POST)
    public ModelAndView authorizationUpdate(@ModelAttribute("applicationVO") @Valid ApplicationVO applicationVO,
            BindingResult bindingResult) {
        ModelAndView page = new ModelAndView("Application");
        if (!bindingResult.hasErrors()) {
            page.addObject("application", applicationService.update(mapper.map(applicationVO, Application.class)));
            page.addObject("applications", applicationService.listApplications());
            page.setViewName("Applications");
        }
        return page;
    }

    /**
     * Application creation route
     * 
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView application() {
        ModelAndView page = new ModelAndView("Application");
        page.addObject("applicationVO", new ApplicationVO());
        return page;
    }

    /**
     * Create new Application
     * 
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView applicationCreate(@ModelAttribute("applicationVO") @Valid ApplicationVO applicationVO,
            BindingResult bindingResult) {
        ModelAndView page = new ModelAndView("Application");
        if (!bindingResult.hasErrors()) {
            applicationService.create(mapper.map(applicationVO, Application.class));
            page.addObject("applications", applicationService.listApplications());
            page.setViewName("Applications");
        }
        return page;
    }

    /**
     * Delete an application permanently
     * 
     * @param auid
     *            : application unique id
     * @return
     */
    @RequestMapping(value = "/{auid}/delete", method = RequestMethod.GET)
    public ModelAndView applicationDelete(@PathVariable String auid) {
        ModelAndView page = new ModelAndView("Applications");
        applicationService.removeApplication(auid);
        page.addObject("applications", applicationService.listApplications());
        return page;
    }
}
