package fi.mongologin.controller;

import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:applicationContext.xml" })
public class MainControllerTest {
    
    protected static final Logger log = LoggerFactory.getLogger(MainControllerTest.class);
    private ApplicationContext applicationContext;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HandlerAdapter handlerAdapter;
    private MainController controller;
  
    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        handlerAdapter = applicationContext.getBean(HandlerAdapter.class);
        controller = new MainController();
    }
    
    @Test
    public void testIndex() throws Exception {
        request.setMethod("GET");
        request.setRequestURI("/index");
        final ModelAndView index = handlerAdapter.handle(request, response, controller);
        assertViewName(index, "redirect:/login");
    }
}