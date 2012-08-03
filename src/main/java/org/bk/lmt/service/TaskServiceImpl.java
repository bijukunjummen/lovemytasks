package org.bk.lmt.service;

import java.util.List;

import org.bk.lmt.dao.TaskUserDao;
import org.bk.lmt.dao.TasksDao;
import org.bk.lmt.domain.TaskUser;
import org.bk.lmt.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired private TasksDao taskDao;
	@Autowired private TaskUserDao gtdUserDao;
	
	@Override
	@Transactional
	public Task persistForUser(Task task, String username) {
		TaskUser user = this.gtdUserDao.findUserByUserName(username);
		task.setTaskUser(user);
		return this.taskDao.persist(task);
	}

	@Override
	@Transactional
	public Task updateForUser(Task task, String username) {
		TaskUser user = this.gtdUserDao.findUserByUserName(username);
		task.setTaskUser(user);
		return this.taskDao.update(task);
	}

	@Override
	public Task findById(Long id) {
		return this.taskDao.findById(id);
	}

	@Override
	public List<Task> findTasksByUser(String username, int firstResult, int maxResults) {
		TaskUser user = this.gtdUserDao.findUserByUserName(username);
		return this.taskDao.findTasksByUser(user, firstResult, maxResults );
	}
	
	@Override
	public List<Task> findTasksByUserAndNameFilter(String username, String namePattern, int firstResult, int maxResults) {
		TaskUser user = this.gtdUserDao.findUserByUserName(username);
		return this.taskDao.findTasksByUserAndNameFilter(user, namePattern, firstResult, maxResults );
	}

	@Override
	public Long countTasksByUser(String username) {
		TaskUser user = this.gtdUserDao.findUserByUserName(username);
		return this.taskDao.countTasksByUser(user);
	}

	@Override
	@Transactional
	public void remove(Task task) {
		this.taskDao.remove(task);
	}



}
