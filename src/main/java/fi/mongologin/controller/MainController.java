package fi.mongologin.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.mongologin.domain.User;
import fi.mongologin.tools.CookieHandler;

@Controller
public class MainController {
    protected static final Logger log = LoggerFactory.getLogger(MainController.class);
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        if("true".equals(CookieHandler.getCookie(request, "loggedin"))) {
            User user = new User();
            model.addAttribute(user);
            return "index";
        }
        return "redirect:/login";
    }
}