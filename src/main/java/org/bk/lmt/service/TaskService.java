package org.bk.lmt.service;

import java.util.List;

import org.bk.lmt.domain.Task;

public interface TaskService {
	Task persistForUser(Task task, String username);
	Task updateForUser(Task task, String username);
	Task findById(Long id);
	List<Task> findTasksByUser(String username, int firstResult, int maxResults);
	List<Task> findTasksByUserAndNameFilter(String username, String namePattern, int firstResult, int maxResults);
	Long countTasksByUser(String username);
	void remove(Task task);
}
