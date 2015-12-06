package org.argonot.authenticator.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * <b>HomeController</b> concern general Authentificator routes
 * @author Meidi
 *
 */
@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * The home page
     */
    private static final String INDEX_PAGE = "Index";
    
    /**
     * Forbidden page
     */
    private static final String FORBIDDEN_PAGE = "errors/403";

    /**
     * Security error page
     */
    private static final String SECURITY_ERROR_PAGE = "errors/hdiv";

    /**
     * Route to the home page which is the API documentation
     * @return Index.jsp
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showDocumentationAPI() {
        return new ModelAndView(INDEX_PAGE);
    }
    
    /**
     * Route to the home page which is the API documentation
     * @return Index.jsp
     */
    @RequestMapping(value = "security/403", method = RequestMethod.GET)
    public ModelAndView forbidden() {
        return new ModelAndView(FORBIDDEN_PAGE);
    }

    /**
     * Route to the security errors
     * 
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "security/error", method = RequestMethod.GET)
    public ModelAndView securityError() {
        return new ModelAndView(SECURITY_ERROR_PAGE);
    }

}
