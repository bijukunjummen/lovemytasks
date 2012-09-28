package org.bk.contextcaching;

import org.bk.lmt.domain.TaskUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
	@Bean
	public TaskUser user1(){
		TaskUser user1 = new TaskUser();
		user1.setFullname("testUser1");
		user1.setUsername("user1");
		return user1;
	}
	
	@Bean
	public TaskUser user2(){
		TaskUser user2 = new TaskUser();
		user2.setFullname("testUser2");
		user2.setUsername("user2");
		return user2;
	}
	
	@Bean
	public DelayBean delayBean(){
		return new DelayBean();
	}
}
