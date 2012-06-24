package org.bk.lmt.dao;

import java.util.List;

import org.bk.lmt.domain.TaskUser;

public interface TaskUserDao extends BaseDao<Long, TaskUser> {
	List<TaskUser> findAllUsers(int firstResult, int maxResults);
	TaskUser findUserByUserName(String userName);
}
