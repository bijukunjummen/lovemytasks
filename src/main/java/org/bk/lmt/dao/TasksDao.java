package org.bk.lmt.dao;

import java.util.List;

import org.bk.lmt.domain.TaskUser;
import org.bk.lmt.domain.Task;

public interface TasksDao extends BaseDao<Long, Task>{
    List<Task> findTasksByUser(TaskUser taskUser);
    List<Task> findTasksByUser(TaskUser taskUser, int firstResult, int maxResults);
    Long countTasksByUser(TaskUser taskUser);
}
 