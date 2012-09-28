package org.bk.webtestjson;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.annotationConfigSetup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.server.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


public class WebContextConfigurationTest {
	
	
	@Test
	public void testAWebFlow() throws Exception {
		MockMvc mockMvc = annotationConfigSetup(WebContextConfigurationTest.TestConfiguration.class)
			.build();
		mockMvc.perform(get("/test/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("test"));
		
	}

	@Configuration
	@ImportResource("classpath:/org/bk/webtestjson/test-config.xml")
	public static class TestConfiguration{
		
	}
}


@Controller
@RequestMapping("/test")
class TestController {
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = "application/json")
	public @ResponseBody EntityWithJAXB readEntityWithJAXB(@PathVariable("id") int id, Model model) {
		EntityWithJAXB entityWithJAXB = new EntityWithJAXB();
		entityWithJAXB.setAnInteger(2);
		entityWithJAXB.setaString("AString");
		
		EntityWithJAXB child = new EntityWithJAXB();
		child.setAnInteger(3);
		child.setaString("AChild");		
		entityWithJAXB.setaChild(child);
		
		child.setaChild(entityWithJAXB);
	    return entityWithJAXB;
	}	
	
}

@XmlAccessorType(XmlAccessType.FIELD)
class EntityWithJAXB {
	
	private int anInteger;
	private String aString;
	
	@XmlTransient
	private EntityWithJAXB aChild;
	
	public int getAnInteger() {
		return anInteger;
	}
	public void setAnInteger(int anInteger) {
		this.anInteger = anInteger;
	}
	public String getaString() {
		return aString;
	}
	public void setaString(String aString) {
		this.aString = aString;
	}
	public EntityWithJAXB getaChild() {
		return aChild;
	}
	public void setaChild(EntityWithJAXB aChild) {
		this.aChild = aChild;
	}
}
