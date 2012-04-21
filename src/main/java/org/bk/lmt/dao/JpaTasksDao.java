package org.bk.lmt.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.bk.lmt.domain.Task;
import org.bk.lmt.domain.GtdUser;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTasksDao extends JpaDao<Long, Task> implements TasksDao{
    public JpaTasksDao(){
        super(Task.class);
    }
    public List<Task> findTasksByGtdUser(GtdUser gtdUser) {
        if (gtdUser == null) throw new IllegalArgumentException("The gtdUser argument is required");
        EntityManager em = this.getEntityManager();
        TypedQuery<Task> q = em.createQuery("SELECT task FROM Task AS task WHERE task.gtdUser = :gtdUser", Task.class);
        q.setParameter("gtdUser", gtdUser);
        return q.getResultList();
    }
    
    public List<Task> findTasksByStartDateLessThanEquals(Date startDate) {
        if (startDate == null) throw new IllegalArgumentException("The startDate argument is required");
        EntityManager em = this.getEntityManager();
        TypedQuery<Task> q = em.createQuery("SELECT task FROM Task AS task WHERE task.startDate <= :startDate", Task.class);
        q.setParameter("startDate", startDate);
        return q.getResultList();
    }

}
