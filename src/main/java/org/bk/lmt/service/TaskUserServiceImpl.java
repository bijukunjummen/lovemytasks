package org.bk.lmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.bk.lmt.dao.TaskUserDao;
import org.bk.lmt.domain.TaskUser;
import org.springframework.stereotype.Service;

@Service
public class TaskUserServiceImpl implements TaskUserService{
	@Resource private TaskUserDao taskUserDao;
	
	@Override
    public List<TaskUser> findAllTaskUsers(int firstResult, int maxResults) {
	    return this.taskUserDao.findAllUsers(firstResult, maxResults);
    }
	
	@Override
    public TaskUser findUserByUserName(String userName) {
		return this.taskUserDao.findUserByUserName(userName);
    }

	public void setTaskUserDao(TaskUserDao taskUserDao) {
    	this.taskUserDao = taskUserDao;
    }

}
