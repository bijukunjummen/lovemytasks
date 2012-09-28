package org.bk.lmt.web;


import org.bk.lmt.domain.TaskUser;
import org.bk.lmt.types.CustomUserDetails;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.server.setup.MockMvcBuilders.*;

public class ContextControllerSpringMvcTest {

	@Test
	public void testContextController() throws Exception{
		TaskUser user = new TaskUser();
		user.setUsername("user1");user.setPassword("");
		Authentication authentication = new UsernamePasswordAuthenticationToken(new CustomUserDetails(user), null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		xmlConfigSetup("classpath:/META-INF/spring/web/webmvc-config.xml","classpath:/META-INF/spring/web/webmvc-config-tiles.xml", "classpath:/org/bk/lmt/web/contextcontrollertest.xml")
			.configureWebAppRootDir("src/main/webapp", false).build()
			.perform(post("/contexts").param("name", "context1"))
			.andExpect(status().isOk())
			.andExpect(view().name("redirect:/contexts"));
		
		xmlConfigSetup("classpath:/META-INF/spring/web/webmvc-config.xml","classpath:/META-INF/spring/web/webmvc-config-tiles.xml", "classpath:/org/bk/lmt/web/contextcontrollertest.xml")
			.configureWebAppRootDir("src/main/webapp", false).build()
			.perform(get("/contexts"))
			.andExpect(status().isOk())
			.andExpect(view().name("contexts/list"));
		
		
	}

}
