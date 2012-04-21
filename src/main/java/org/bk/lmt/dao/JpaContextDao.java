package org.bk.lmt.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.bk.lmt.domain.Context;
import org.springframework.stereotype.Repository;

@Repository
public class JpaContextDao extends JpaDao<Long, Context> implements ContextDao{

    public JpaContextDao(){
        super(Context.class);
    }
	@Override
	public List<Context> findContextEntries(int firstResult, int maxResults) {
		return this.entityManager.createQuery("select o from Context o", Context.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}



	@Override
	public List<Context> findContextsByName(String name) {
		if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Context> c = cb.createQuery(Context.class);
		Root<Context> contexts = c.from(Context.class);
		c.select(contexts).where(cb.equal(contexts.get("name"), name));
		
		
		TypedQuery<Context> q = this.entityManager.createQuery(c);
		return q.getResultList();
	}


	@Override
    public List<Context> findContextsByGtdUser(String userName, int firstResult, int maxResults) {
        TypedQuery<Context> q = this.entityManager.createQuery("SELECT o FROM Context o WHERE o.gtdUser.username = :userName order by o.name", Context.class);
        q.setParameter("userName", userName);
        return q.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }


	@Override
    public Long countContextsByUserName(String userName) {
		TypedQuery<Long> q = this.getEntityManager().createQuery("select count(o) from Context o where o.gtdUser.username=:userName", Long.class);
        q.setParameter("userName", userName);
		return q.getSingleResult();
    }

}
