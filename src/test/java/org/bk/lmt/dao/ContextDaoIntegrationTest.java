package org.bk.lmt.dao;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


import java.util.List;
import java.util.Map;

import org.bk.lmt.dao.ContextDao;
import org.bk.lmt.dao.GtdUserDao;
import org.bk.lmt.domain.Context;
import org.bk.lmt.domain.GtdUser;
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
	@Autowired Map<String, GtdUser> gtdUsersMap;
	@Autowired ContextDao contextDao;
	@Autowired GtdUserDao gtdUserDao;

	@Before
	public void setUp() {
		this.gtdUserDao.persist(gtdUsersMap.get("user1"));
		for (String key : contextsMap.keySet()) {
			Context gtdContext = contextsMap.get(key);
			gtdContext.setGtdUser(gtdUsersMap.get("user1"));
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

		List<Context> contextsByName = this.contextDao.findContextsByName("context5");
		
		assertThat(contextsByName, hasItem(contextsMap.get("context5")));

		this.contextDao.remove(aContext);
		assertThat(this.contextDao.findById(1L), is(nullValue()));
	}

}
