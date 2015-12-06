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

    private static final String APPLICATION_HTTP_ATTR = "application";

    private static final String PROVIDED_APPLICATION_HTTP_ATTR = "applicationVO";

    private static final String UPDATE_OR_CREATE_ACTION_HTTP_ATTR = "action";

    private static final String APPLICATION_PAGE_HTTP_ATTR = "Application";

    private static final String APPLICATION_LIST_HTTP_ATTR = "applications";

    private static final String APPLICATION_LIST_PAGE_HTTP_ATTR = "Applications";

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
        ModelAndView page = new ModelAndView(APPLICATION_LIST_PAGE_HTTP_ATTR);
        page.addObject(APPLICATION_LIST_HTTP_ATTR, applicationService.listApplications());
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
        ModelAndView page = new ModelAndView(APPLICATION_PAGE_HTTP_ATTR);
        page.addObject(PROVIDED_APPLICATION_HTTP_ATTR, new ApplicationVO());
        page.addObject(APPLICATION_HTTP_ATTR, applicationService.find(auid));
        page.addObject(UPDATE_OR_CREATE_ACTION_HTTP_ATTR, "update");
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
    public ModelAndView authorizationUpdate(@ModelAttribute(PROVIDED_APPLICATION_HTTP_ATTR) @Valid ApplicationVO applicationVO,
            BindingResult bindingResult) {
        ModelAndView page = new ModelAndView(APPLICATION_PAGE_HTTP_ATTR);
        if (!bindingResult.hasErrors()) {
            page.addObject(APPLICATION_HTTP_ATTR, applicationService.update(mapper.map(applicationVO, Application.class)));
            page.addObject(APPLICATION_LIST_HTTP_ATTR, applicationService.listApplications());
            page.setViewName(APPLICATION_LIST_PAGE_HTTP_ATTR);
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
        ModelAndView page = new ModelAndView(APPLICATION_PAGE_HTTP_ATTR);
        page.addObject(PROVIDED_APPLICATION_HTTP_ATTR, new ApplicationVO());
        return page;
    }

    /**
     * Create new Application
     * 
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView applicationCreate(@ModelAttribute(PROVIDED_APPLICATION_HTTP_ATTR) @Valid ApplicationVO applicationVO,
            BindingResult bindingResult) {
        ModelAndView page = new ModelAndView(APPLICATION_PAGE_HTTP_ATTR);
        if (!bindingResult.hasErrors()) {
            applicationService.create(mapper.map(applicationVO, Application.class));
            page.addObject(APPLICATION_LIST_HTTP_ATTR, applicationService.listApplications());
            page.setViewName(APPLICATION_LIST_PAGE_HTTP_ATTR);
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
        ModelAndView page = new ModelAndView(APPLICATION_LIST_PAGE_HTTP_ATTR);
        applicationService.removeApplication(auid);
        page.addObject(APPLICATION_LIST_HTTP_ATTR, applicationService.listApplications());
        return page;
    }
}
