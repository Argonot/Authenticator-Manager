package org.argonot.authenticator.manager.controller;

import javax.validation.Valid;

import org.argonot.authenticator.business.entity.User;
import org.argonot.authenticator.business.service.UserService;
import org.argonot.authenticator.manager.vo.UserVO;
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
 * <b>AdminUserController</b> operate for HTTP transactions from the BO for
 * users administration
 * 
 * @author Meidi
 *
 */
@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private static final String UPDATE_OR_CREATE_ACTION_HTTP_ATTR = "action";

    private static final String USER_HTTP_ATTR = "user";

    private static final String PROVIDED_USER_HTTP_ATTR = "userVO";

    private static final String USER_PAGE_HTTP_ATTR = "User";

    private static final String USER_LIST_HTTP_ATTR = "users";

    private static final String USER_LIST_PAGE_HTTP_ATTR = "Users";

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    /**
     * Presents all the users in a tab
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView users() {
        ModelAndView page = new ModelAndView(USER_LIST_PAGE_HTTP_ATTR);
        page.addObject(USER_LIST_HTTP_ATTR, userService.listUsers());
        return page;
    }

    /**
     * Display a user
     * 
     * @param idUser
     *            : user unique id
     * @return
     */
    @RequestMapping(value = "/{idUser}/update", method = RequestMethod.GET)
    public ModelAndView user(@PathVariable long idUser) {
        ModelAndView page = new ModelAndView(USER_PAGE_HTTP_ATTR);
        page.addObject(PROVIDED_USER_HTTP_ATTR, new UserVO());
        page.addObject(USER_HTTP_ATTR, userService.find(idUser));
        page.addObject(UPDATE_OR_CREATE_ACTION_HTTP_ATTR, "update");
        return page;
    }

    /**
     * Update user information
     * 
     * @param idUser
     *            : user unique id
     * @return
     */
    @RequestMapping(value = "/{idUser}/update", method = RequestMethod.POST)
    public ModelAndView userUpdate(@PathVariable long idUser, @ModelAttribute(PROVIDED_USER_HTTP_ATTR) @Valid UserVO userVO,
            BindingResult bindingResult) {
        ModelAndView page = new ModelAndView(USER_PAGE_HTTP_ATTR);
        if (!bindingResult.hasErrors()) {
            page.addObject(USER_HTTP_ATTR, userService.update(mapper.map(userVO, User.class), idUser));
            page.addObject(USER_LIST_HTTP_ATTR, userService.listUsers());
            page.setViewName(USER_LIST_PAGE_HTTP_ATTR);
        }
        return page;
    }

    /**
     * User creation route
     * 
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView user() {
        ModelAndView page = new ModelAndView(USER_PAGE_HTTP_ATTR);
        page.addObject(PROVIDED_USER_HTTP_ATTR, new UserVO());
        return page;
    }

    /**
     * Create new User
     * 
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView userCreate(@ModelAttribute(PROVIDED_USER_HTTP_ATTR) @Valid UserVO userVO, BindingResult bindingResult) {
        ModelAndView page = new ModelAndView(USER_PAGE_HTTP_ATTR);
        if (!bindingResult.hasErrors()) {
            userService.create(mapper.map(userVO, User.class));
            page.addObject(USER_LIST_HTTP_ATTR, userService.listUsers());
            page.setViewName(USER_LIST_PAGE_HTTP_ATTR);
        }
        return page;
    }

    /**
     * Unlock user
     * 
     * @param idUser
     *            : user unique id
     * @return
     */
    @RequestMapping(value = "/{idUser}/unlock", method = RequestMethod.GET)
    public ModelAndView unlockUser(@PathVariable long idUser) {
        ModelAndView page = new ModelAndView(USER_LIST_PAGE_HTTP_ATTR);
        userService.unlockUser(idUser);
        page.addObject(USER_LIST_HTTP_ATTR, userService.listUsers());
        return page;
    }

    /**
     * Delete a User permanently
     * 
     * @param idUser
     *            : user unique id
     * @return
     */
    @RequestMapping(value = "/{idUser}/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable long idUser) {
        ModelAndView page = new ModelAndView(USER_LIST_PAGE_HTTP_ATTR);
        userService.removeUser(idUser);
        page.addObject(USER_LIST_HTTP_ATTR, userService.listUsers());
        return page;
    }
}
