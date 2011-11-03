package fi.mongologin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.mongologin.domain.User;
import fi.mongologin.service.UserService;

@Controller
public class SignUpController {
    
    @Resource(name="userService")
    private UserService userService;
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "signup";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute User user, Model model) {
        if(user.getUserName() != null && user.getPassword() != null) {
            userService.insert(user);
            return "redirect:/login";
        }
        return "signup";
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}