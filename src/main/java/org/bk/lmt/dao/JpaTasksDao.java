package org.bk.lmt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.bk.lmt.domain.TaskUser;
import org.bk.lmt.domain.Task;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTasksDao extends JpaDao<Long, Task> implements TasksDao{
    public JpaTasksDao(){
        super(Task.class);
    }
    
    public List<Task> findTasksByUser(TaskUser taskUser) {
        if (taskUser == null) throw new IllegalArgumentException("The taskUser argument is required");
        EntityManager em = this.getEntityManager();
        TypedQuery<Task> q = em.createNamedQuery("Task.findByUser", Task.class);
        q.setParameter("taskUser", taskUser);
        return q.getResultList();
    }

	@Override
	public List<Task> findTasksByUser(TaskUser taskUser, int firstResult, int maxResults) {
        if (taskUser == null) throw new IllegalArgumentException("The taskUser argument is required");
        EntityManager em = this.getEntityManager();
        TypedQuery<Task> q = em.createNamedQuery("Task.findByUser", Task.class);
        q.setParameter("taskUser", taskUser);
        return q.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}

	@Override
	public Long countTasksByUser(TaskUser taskUser) {
		if (taskUser==null) throw new IllegalArgumentException("The taskUser argument is required");
		EntityManager em = this.getEntityManager();
		TypedQuery<Long> q = em.createNamedQuery("Task.countByUser", Long.class);
		q.setParameter("taskUser", taskUser);
		return q.getSingleResult();
	}
}
