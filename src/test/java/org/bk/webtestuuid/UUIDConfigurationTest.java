package org.bk.webtestuuid;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.annotationConfigSetup;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.server.MockMvc;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class UUIDConfigurationTest {
	
	
	@Test
	public void testAWebFlow() throws Exception {
		MockMvc mockMvc = annotationConfigSetup(UUIDConfigurationTest.TestConfiguration.class)
			.build();
		
		mockMvc.perform(get("/strings/1,2,3"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("\"1\"")));

		mockMvc.perform(get("/file/teST.ext"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("test")));
		

//		mockMvc.perform(get("/uuids/48e977df-3c30-4988-af5d-2e2275945424,48e977df-3c30-4988-af5d-2e2275945424"))
//		.andExpect(status().isOk())
//		.andExpect(content().string(containsString("\"48e977df-3c30-4988-af5d-2e2275945424\"")));
//
//		mockMvc.perform(get("/uuid/48e977df-3c30-4988-af5d-2e2275945424"))
//		.andExpect(status().isOk())
//		.andExpect(content().string(containsString("\"48e977df-3c30-4988-af5d-2e2275945424\"")));

		
	}

	@Configuration
	@ComponentScan(basePackages="org.bk.webtestuuid")
	public static class TestConfiguration extends WebMvcConfigurationSupport{

		@Bean
		public PathMatcher pathMatcher(){
			return new CaseInsensitivePathMatcher();
		}
		@Bean
		public RequestMappingHandlerMapping requestMappingHandlerMapping() {
			RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
			handlerMapping.setOrder(0);
			handlerMapping.setInterceptors(getInterceptors());
			handlerMapping.setPathMatcher(pathMatcher());
			return handlerMapping;
		}
	}
}

@Controller
class UUIDController{
	
	@RequestMapping(value = "/strings/{strings}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> strings(@PathVariable("strings") List<String> strings) {
        return strings;
    }
	
	@RequestMapping(value = "/file/{file}.{ext}", method = RequestMethod.GET)
    @ResponseBody
    public String file(@PathVariable("file") String file, @PathVariable("ext") String ext) {
		System.out.println(file);
		System.out.println(ext);
        return file;
    }

	@RequestMapping(value = "/uuid/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public UUID get(@PathVariable("uuid") UUID uuid) {
        return uuid;
    }

	
	@RequestMapping(value = "/uuids/{uuids}", method = RequestMethod.GET)
    @ResponseBody
    public List<UUID> list(@PathVariable("uuids") List<UUID> uuids) {
        return uuids;
    }
	

}

class UUIDConverter implements Converter<String, UUID> {

	@Override
	public UUID convert(String source) {
		return UUID.fromString(source);
	}
	
}
