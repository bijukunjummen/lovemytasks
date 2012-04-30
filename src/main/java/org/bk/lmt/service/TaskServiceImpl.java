package org.bk.lmt.service;

import java.util.List;

import org.bk.lmt.dao.GtdUserDao;
import org.bk.lmt.dao.TasksDao;
import org.bk.lmt.domain.GtdUser;
import org.bk.lmt.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired private TasksDao taskDao;
	@Autowired private GtdUserDao gtdUserDao;
	
	@Override
	public Task persistForUser(Task task, String username) {
		GtdUser user = this.gtdUserDao.findUserByUserName(username);
		task.setGtdUser(user);
		return this.taskDao.persist(task);
	}

	@Override
	public Task updateForUser(Task task, String username) {
		GtdUser user = this.gtdUserDao.findUserByUserName(username);
		task.setGtdUser(user);
		return this.taskDao.update(task);
	}

	@Override
	public Task findById(Long id) {
		return this.taskDao.findById(id);
	}

	@Override
	public List<Task> findTasksByUser(String username, int firstResult, int maxResults) {
		GtdUser user = this.gtdUserDao.findUserByUserName(username);
		return this.taskDao.findTasksByGtdUser(user, firstResult, maxResults );
	}

	@Override
	public Long countTasksByUser(String username) {
		GtdUser user = this.gtdUserDao.findUserByUserName(username);
		return this.taskDao.countTasksByUser(user);
	}

	@Override
	public void remove(Task task) {
		this.taskDao.remove(task);
	}

}
