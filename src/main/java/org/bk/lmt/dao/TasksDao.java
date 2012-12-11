package org.bk.lmt.dao;

import java.util.List;

import org.bk.lmt.domain.TaskUser;
import org.bk.lmt.domain.Task;

public interface TasksDao extends BaseDao<Long, Task>{
    List<Task> findTasksByUser(TaskUser taskUser);
    List<Task> findTasksByUser(TaskUser taskUser, int firstResult, int maxResults);
    Long countTasksByUser(TaskUser taskUser);
    Long countIncompleteTasksByUser(TaskUser taskUser);
	List<Task> findTasksByUserAndNameFilter(TaskUser user, String namePattern, int firstResult, int maxResults);
	List<Task> findIncompleteTasksByUser(TaskUser user, int firstResult, int maxResults);
}
 