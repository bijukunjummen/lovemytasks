package org.bk.lmt.dao;

import java.util.List;

import org.bk.lmt.domain.GtdUser;
import org.bk.lmt.domain.Task;

public interface TasksDao extends BaseDao<Long, Task>{
    List<Task> findTasksByGtdUser(GtdUser gtdUser);
    List<Task> findTasksByGtdUser(GtdUser gtdUser, int firstResult, int maxResults);
    Long countTasksByUser(GtdUser gtdUser);
}
 