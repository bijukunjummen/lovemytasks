package org.bk.lmt.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.bk.lmt.domain.Project;
import org.springframework.stereotype.Repository;

@Repository
public class JpaProjectDao extends JpaDao<Long, Project> implements ProjectDao{
    public JpaProjectDao(){
        super(Project.class);
    }
    
	@Override
	public List<Project> findProjectsByGtdUser(String userName, int firstResult, int maxResults) {
		TypedQuery<Project> q = this.entityManager.createNamedQuery("Project.findByUser", Project.class);
		q.setParameter("userName", userName);
		return q.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}
	
	@Override
	public List<Project> findProjectsByGtdUser(String userName) {
		TypedQuery<Project> q = this.entityManager.createNamedQuery("Project.findByUser", Project.class);
		q.setParameter("userName", userName);
		return q.getResultList();
	}
	
    @Override
    public Long countProjectsByUserName(String userName) {
        TypedQuery<Long> q = this.entityManager.createNamedQuery("Project.countByUser", Long.class);
        q.setParameter("userName", userName);
        return q.getSingleResult();
    }
	
	
}
