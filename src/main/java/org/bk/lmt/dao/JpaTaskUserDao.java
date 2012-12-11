package org.bk.lmt.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.bk.lmt.domain.TaskUser;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTaskUserDao extends JpaDao<Long, TaskUser> implements TaskUserDao {

    public JpaTaskUserDao(){
        super(TaskUser.class);
    }
	@Override
	public List<TaskUser> findAllUsers(int firstResult, int maxResults) {
		return getEntityManager()
				.createNamedQuery("TaskUser.findAll", TaskUser.class)
				.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}

	@Override
    public TaskUser findUserByUserName(String username) {
		TypedQuery<TaskUser> q = this.getEntityManager().createNamedQuery("TaskUser.findUserByUserName", TaskUser.class);
		q.setParameter("username", username);
		List<TaskUser> list = q.getResultList();
		if (list==null || list.size()==0){
			return null;
		}
		
		return list.get(0);
    }
}
