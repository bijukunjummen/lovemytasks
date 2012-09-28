package org.bk.contextcaching;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


import java.util.Map;

import org.bk.lmt.domain.TaskUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfiguration.class})
public class Test2 {
	@Autowired Map<String, TaskUser> usersMap;


	@Test
	public void testGetAUser() {
		TaskUser user = usersMap.get("user1");
		assertThat(user.getFullname(), is("testUser1"));
	}

}
