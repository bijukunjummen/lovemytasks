package org.bk.lmt.dao;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.List;
import java.util.Map;

import org.bk.lmt.dao.ProjectDao;
import org.bk.lmt.dao.TaskUserDao;
import org.bk.lmt.domain.Project;
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
@ContextConfiguration(locations = { "projecttest.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ProjectDaoIntegrationTest {

	@Autowired Map<String, Project> projectsMap;

	@Autowired Map<String, TaskUser> usersMap;

	@Autowired ProjectDao projectDao;

	@Autowired TaskUserDao taskUserDao;

	@Before
	public void setUp() {
		this.taskUserDao.persist(usersMap.get("user1"));
		for (String key : projectsMap.keySet()) {
			Project gtdProject = projectsMap.get(key);
			gtdProject.setTaskUser(usersMap.get("user1"));
			this.projectDao.persist(gtdProject);
		}
	}

	@Test
	public void testProjectIntegration() {
		Project gtdProject = this.projectDao.findById(1L);
		assertThat(gtdProject, is(equalTo(projectsMap.get("project1"))));
		
		TaskUser user1 = this.taskUserDao.findUserByUserName("user1");
		List<Project> user1Projects = this.projectDao.findProjectsByGtdUser(user1.getUsername(), 0, 10);
		assertThat(user1Projects, hasItems(projectsMap.values().toArray(new Project[0])));
	
		assertThat(this.projectDao.countProjectsByUserName(user1.getUsername()), is(5L));
		this.projectDao.remove(gtdProject);
		assertThat( this.projectDao.findById(1L), is(nullValue()));
	}
}
