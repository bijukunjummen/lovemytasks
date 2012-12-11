package org.bk.lmt.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.bk.lmt.domain.Context;
import org.springframework.stereotype.Repository;

@Repository
public class JpaContextDao extends JpaDao<Long, Context> implements ContextDao{

    public JpaContextDao(){
        super(Context.class);
    }
	
	@Override
    public List<Context> findContextsByGtdUser(String userName, int firstResult, int maxResults) {
        TypedQuery<Context> q = this.entityManager.createNamedQuery("Context.findContextByTaskUser", Context.class);
        q.setParameter("userName", userName);
        return q.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Override
    public List<Context> findContextsByGtdUser(String userName) {
        TypedQuery<Context> q = this.entityManager.createNamedQuery("Context.findContextByTaskUser", Context.class);
        q.setParameter("userName", userName);
        return q.getResultList();
    }
	@Override
    public Long countContextsByUserName(String userName) {
		TypedQuery<Long> q = this.getEntityManager().createNamedQuery("Context.countContextByTaskUser", Long.class);
        q.setParameter("userName", userName);
		return q.getSingleResult();
    }
}
