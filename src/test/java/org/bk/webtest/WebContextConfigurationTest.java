package org.bk.webtest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.* ;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.server.setup.MockMvcBuilders.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.server.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

public class WebContextConfigurationTest {
	private final String createJson = "{\"id\":%d,\"first\":\"%s\",\"last\":\"%s\"}";
	private final String membersJson = "[{\"id\":1,\"first\":\"One\",\"last\":\"One\"},{\"id\":2,\"first\":\"Two\",\"last\":\"Two\"},{\"id\":3,\"first\":\"Three\",\"last\":\"Three\"}]";
	private final String updatedMembersJson = "[{\"id\":1,\"first\":\"One\",\"last\":\"OneUpdated\"},{\"id\":2,\"first\":\"Two\",\"last\":\"Two\"},{\"id\":3,\"first\":\"Three\",\"last\":\"Three\"}]";
	
	
	@Test
	public void testAWebFlow() throws Exception {
		MockMvc mockMvc = annotationConfigSetup(WebContextConfigurationTest.TestConfiguration.class)
			.build();
		mockMvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON).body(String.format(createJson, 1,"One","One").getBytes()))
				.andExpect(status().isOk());
		mockMvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON).body(String.format(createJson, 2,"Two","Two").getBytes()))
			.andExpect(status().isOk());
		mockMvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON).body(String.format(createJson, 3,"Three","Three").getBytes()))
			.andExpect(status().isOk());
		
		
		mockMvc.perform(get("/members").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(membersJson)));
		
		mockMvc.perform(put("/members").contentType(MediaType.APPLICATION_JSON).body(String.format(createJson, 1,"One","OneUpdated").getBytes()))
			.andExpect(status().isOk());
		
		mockMvc.perform(get("/members").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].first").value("One"));
		
		mockMvc.perform(get("/members/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string(String.format(createJson, 1,"One","OneUpdated")));
		
		mockMvc.perform(delete("/members/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		
		
		
	}

	@Configuration
	@EnableWebMvc
	@ComponentScan(basePackages="org.bk.webtest")
	public static class TestConfiguration{
		
	}
}

@Controller
class MembersController{
	private Map<Integer, Member> memberDB = new HashMap<>();
	@RequestMapping(value = "/members", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Member> list() {
        return this.memberDB.values();
    }
	
	@RequestMapping(value="/members", method=RequestMethod.POST)
	public @ResponseBody Member create(@RequestBody Member member){
		this.memberDB.put(member.getId(), member);
		return member;
	}
	
	@RequestMapping(value="/members", method=RequestMethod.PUT)
	public @ResponseBody Member update(@RequestBody Member member){
		this.memberDB.put(member.getId(), member);
		return member;
	}
	
	@RequestMapping(value="/members/{id}", method=RequestMethod.GET)
	public @ResponseBody Member get(@PathVariable("id") Integer id){
		return this.memberDB.get(id);
	}
	
	@RequestMapping(value="/members/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id){
		this.memberDB.remove(id);
	}
}

class Member{
	private Integer id;
	private String first;
	private String last;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}
	
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
}
