package org.bk.webtest2;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.server.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.server.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

public class WebContextConfigurationTest {

	@Test
	public void testAWebFlow() throws Exception {
		MockMvc mockMvc = annotationConfigSetup(WebContextConfigurationTest.TestConfiguration.class).build();
		mockMvc.perform(get("/sample")).andExpect(view().name("sampleProperView"));
		mockMvc.perform(get("/section")).andExpect(view().name("sectionView"));
	}

	@Configuration
	@EnableWebMvc
	@ComponentScan(basePackages = "org.bk.webtest2")
	public static class TestConfiguration {
		//
	}
	
}

@Controller
class TestController {

	@RequestMapping(value = "/{section}")
	public String section(@PathVariable("section") String section) {
		return section + "View";

	}
	
	@RequestMapping(value = "/sample")
	public String sample() {
		return "sampleProperView";
	}


}
