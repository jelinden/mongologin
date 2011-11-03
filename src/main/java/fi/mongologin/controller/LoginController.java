package fi.mongologin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.mongologin.domain.User;
import fi.mongologin.service.UserService;
import fi.mongologin.tools.CookieHandler;

@Controller
public class LoginController {
    
    @Resource(name="userService")
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@ModelAttribute User user, HttpServletResponse response, Model model) {
        boolean loggedIn = userService.checkPassword(user.getUserName(), user.getPassword());
        if(loggedIn) {
            CookieHandler.setCookie(response, "loggedin", "true");
            return "redirect:/index";
        }
        else {
            model.addAttribute("error", "Login failed.");
            return "redirect:/login";
        }
    }
    
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}