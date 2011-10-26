package fi.mongologin.controller;

import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import fi.mongologin.domain.User;
import fi.mongologin.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:applicationContext.xml" })
public class SignUpControllerTest {
    
    protected static final Logger log = LoggerFactory.getLogger(SignUpControllerTest.class);
    private ApplicationContext applicationContext;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HandlerAdapter handlerAdapter;
    private SignUpController controller;
  
    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        handlerAdapter = applicationContext.getBean(HandlerAdapter.class);
        controller = new SignUpController();
        controller.setUserService(applicationContext.getBean(UserService.class));
    }
    
    @Test
    public void testSignUpGet() throws Exception {
        request.setMethod("GET");
        request.setRequestURI("/signup");
        final ModelAndView signup = handlerAdapter.handle(request, response, controller);
        assertViewName(signup, "signup");
    }
    
    @Test
    public void testSignUp() throws Exception {
        request.setMethod("POST");
        request.setRequestURI("/signup");
        Map<String,String> userMap = new HashMap<String,String>();
        userMap.put("userName", "test");
        userMap.put("password", "password");
        request.addParameters(userMap);
        final ModelAndView signup = handlerAdapter.handle(request, response, controller);
        //signup succeeds
        assertViewName(signup, "login");
    }
}