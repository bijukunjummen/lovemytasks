package org.bk.lmt.dao;

import java.util.Date;
import java.util.List;

import org.bk.lmt.domain.Task;
import org.bk.lmt.domain.GtdUser;

public interface TasksDao extends BaseDao<Long, Task>{
    List<Task> findTasksByGtdUser(GtdUser gtdUser);
    
    List<Task> findTasksByStartDateLessThanEquals(Date startDate);

}
