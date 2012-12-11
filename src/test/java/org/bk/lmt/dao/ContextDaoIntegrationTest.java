package org.bk.lmt.dao;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


import java.util.List;
import java.util.Map;

import org.bk.lmt.dao.ContextDao;
import org.bk.lmt.dao.TaskUserDao;
import org.bk.lmt.domain.Context;
import org.bk.lmt.domain.TaskUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "contexttest.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ContextDaoIntegrationTest {
	@Autowired Map<String, Context> contextsMap;
	@Autowired Map<String, TaskUser> usersMap;
	@Autowired ContextDao contextDao;
	@Autowired TaskUserDao taskUserDao;

	@Before
	public void setUp() {
		System.out.println(this.usersMap.get("user1"));
		this.taskUserDao.persist(usersMap.get("user1"));
		for (String key : contextsMap.keySet()) {
			Context gtdContext = contextsMap.get(key);
			gtdContext.setTaskUser(usersMap.get("user1"));
			gtdContext = this.contextDao.persist(gtdContext);
		}
	}

	@Test
	public void testGetContext() {
		Context aContext = this.contextDao.findById(1L);
		assertThat(aContext.getName(), is("context1"));

		aContext.setName("updated-context1");
		
		aContext = this.contextDao.update(aContext);
		assertThat(aContext.getName(), is("updated-context1"));

		List<Context> contextsByUser = this.contextDao.findContextsByGtdUser("user1",0,10);
		assertThat(contextsByUser, hasItems(contextsMap.get("context1"), contextsMap.get("context2"), contextsMap.get("context3")));


		this.contextDao.remove(aContext);
		assertThat(this.contextDao.findById(1L), is(nullValue()));
	}

}
