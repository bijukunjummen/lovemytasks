package org.bk.lmt.dao;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bk.lmt.domain.TaskUser;
import org.bk.lmt.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("tasktest.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class TaskDaoTest {

	@Autowired TasksDao taskDao;
	@Autowired TaskUserDao taskUserDao;
	@Autowired Map<String, TaskUser> usersMap;
	
	@Autowired Map<String, Task > taskMap;
	
	@Test
	public void testTaskDaoForSimpleOperation() {
		this.taskUserDao.persist(usersMap.get("user1"));
		for (Entry<String, Task> entry:this.taskMap.entrySet()){
			entry.getValue().setTaskUser(usersMap.get("user1"));
			this.taskDao.persist(entry.getValue());
		}
		
		assertThat(this.taskDao.findById(1L), hasProperty("title", equalTo("task1")));
		List<Task> tasksForUser = this.taskDao.findTasksByUser(usersMap.get("user1"));
		assertThat(tasksForUser, hasSize(1));
		assertThat(tasksForUser.get(0), hasProperty("title", equalTo("task1")));
		
		assertThat(this.taskDao.countTasksByUser(usersMap.get("user1")), equalTo(1l));
	}

	
}
