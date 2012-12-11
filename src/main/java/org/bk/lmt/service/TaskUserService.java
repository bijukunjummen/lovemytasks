package org.bk.lmt.service;

import java.util.List;

import org.bk.lmt.domain.TaskUser;

public interface TaskUserService {
	List<TaskUser> findAllTaskUsers(int firstResult, int maxResults);
	TaskUser findUserByUserName(String userName);
}
