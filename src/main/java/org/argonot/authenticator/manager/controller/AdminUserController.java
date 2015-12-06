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
        ModelAndView page = new ModelAndView("Users");
        page.addObject("users", userService.listUsers());
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
        ModelAndView page = new ModelAndView("User");
        page.addObject("userVO", new UserVO());
        page.addObject("user", userService.find(idUser));
        page.addObject("action", "update");
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
    public ModelAndView userUpdate(@PathVariable long idUser, @ModelAttribute("userVO") @Valid UserVO userVO,
            BindingResult bindingResult) {
        ModelAndView page = new ModelAndView("User");
        if (!bindingResult.hasErrors()) {
            page.addObject("user", userService.update(mapper.map(userVO, User.class), idUser));
            page.addObject("users", userService.listUsers());
            page.setViewName("Users");
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
        ModelAndView page = new ModelAndView("User");
        page.addObject("userVO", new UserVO());
        return page;
    }

    /**
     * Create new User
     * 
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView userCreate(@ModelAttribute("userVO") @Valid UserVO userVO, BindingResult bindingResult) {
        ModelAndView page = new ModelAndView("User");
        if (!bindingResult.hasErrors()) {
            userService.create(mapper.map(userVO, User.class));
            page.addObject("users", userService.listUsers());
            page.setViewName("Users");
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
        ModelAndView page = new ModelAndView("Users");
        userService.unlockUser(idUser);
        page.addObject("users", userService.listUsers());
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
        ModelAndView page = new ModelAndView("Users");
        userService.removeUser(idUser);
        page.addObject("users", userService.listUsers());
        return page;
    }
}
