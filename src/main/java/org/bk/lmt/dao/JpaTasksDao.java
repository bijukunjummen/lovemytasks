package org.bk.lmt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.bk.lmt.domain.GtdUser;
import org.bk.lmt.domain.Task;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTasksDao extends JpaDao<Long, Task> implements TasksDao{
    public JpaTasksDao(){
        super(Task.class);
    }
    
    public List<Task> findTasksByGtdUser(GtdUser gtdUser) {
        if (gtdUser == null) throw new IllegalArgumentException("The gtdUser argument is required");
        EntityManager em = this.getEntityManager();
        TypedQuery<Task> q = em.createNamedQuery("Task.findByUser", Task.class);
        q.setParameter("gtdUser", gtdUser);
        return q.getResultList();
    }

	@Override
	public List<Task> findTasksByGtdUser(GtdUser gtdUser, int firstResult, int maxResults) {
        if (gtdUser == null) throw new IllegalArgumentException("The gtdUser argument is required");
        EntityManager em = this.getEntityManager();
        TypedQuery<Task> q = em.createNamedQuery("Task.findByUser", Task.class);
        q.setParameter("gtdUser", gtdUser);
        return q.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}

	@Override
	public Long countTasksByUser(GtdUser gtdUser) {
		if (gtdUser==null) throw new IllegalArgumentException("The gtdUser argument is required");
		EntityManager em = this.getEntityManager();
		TypedQuery<Long> q = em.createNamedQuery("Task.countByUser", Long.class);
		q.setParameter("gtdUser", gtdUser);
		return q.getSingleResult();
	}
}
