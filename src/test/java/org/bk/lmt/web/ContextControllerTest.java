package org.bk.lmt.web;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Map;

import org.bk.lmt.dao.ContextDao;
import org.bk.lmt.dao.TaskUserDao;
import org.bk.lmt.domain.Context;
import org.bk.lmt.domain.TaskUser;
import org.bk.lmt.types.CustomUserDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/web/webmvc-config.xml", "contextcontrollertest.xml"})
public class ContextControllerTest {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;
    
	@Autowired Map<String, Context> contextsMap;
	@Autowired Map<String, TaskUser> usersMap;
	@Autowired ContextDao contextDao;
	@Autowired TaskUserDao taskUserDao;
    
	@Before
	public void setUp() {
		this.taskUserDao.persist(usersMap.get("user1"));
		for (String key : contextsMap.keySet()) {
			Context gtdContext = contextsMap.get(key);
			gtdContext.setTaskUser(usersMap.get("user1"));
			gtdContext = this.contextDao.persist(gtdContext);
		}
	}
	@Test
	public void testContextController() throws Exception{
		MockHttpServletRequest httpRequest = new MockHttpServletRequest("POST","/contexts");
		httpRequest.addParameter("name", "context1");
		
		httpRequest.setAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE,new FlashMap());
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		Authentication authentication = new UsernamePasswordAuthenticationToken(new CustomUserDetails(this.usersMap.get("user1")), null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		HandlerExecutionChain handlerChain = this.handlerMapping.getHandler(httpRequest);
//		handlerChain.getInterceptors();
		Object handler = handlerChain.getHandler();
		ModelAndView modelAndView = handlerAdapter.handle(httpRequest, response, handler);
		assertThat(modelAndView.getViewName(), is("redirect:/contexts"));
	}

}
