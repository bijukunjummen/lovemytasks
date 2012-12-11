package org.bk.lmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.bk.lmt.dao.ContextDao;
import org.bk.lmt.dao.TaskUserDao;
import org.bk.lmt.domain.Context;
import org.bk.lmt.domain.TaskUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContextServiceImpl implements ContextService{

	@Resource private ContextDao contextDao;
	@Resource private TaskUserDao gtdUserDao;

	@Override
	@Transactional
    public Context persistForUser(Context gtdContext, String userName) {
		TaskUser taskUser = this.gtdUserDao.findUserByUserName(userName);
		gtdContext.setTaskUser(taskUser);
	    return contextDao.persist(gtdContext);
    }	
	

	@Override
    public Context findById(Long id) {
		return this.contextDao.findById(id);
    }


	@Override
    public List<Context> findContextsByGtdUserName(String userName, int firstResult, int maxResults) {
		return this.contextDao.findContextsByGtdUser(userName,firstResult, maxResults);    
	}
	
	@Override
    public List<Context> findContextsByGtdUserName(String userName) {
		return this.contextDao.findContextsByGtdUser(userName);    
	}
	@Override
	public Long countContextsByUserName(String userName){
		return this.contextDao.countContextsByUserName(userName);
	}

	@Override
	@Transactional
    public Context updateForUser(Context gtdContext, String userName) {
		TaskUser gtdUser = this.gtdUserDao.findUserByUserName(userName);
		gtdContext.setTaskUser(gtdUser);		
		return this.contextDao.update(gtdContext);
    }

	@Override
	@Transactional
    public void remove(Context context) {
	    this.contextDao.remove(context);
	    
    }


	public void setContextDao(ContextDao contextDao) {
    	this.contextDao = contextDao;
    }

	public void setGtdUserDao(TaskUserDao gtdUserDao) {
    	this.gtdUserDao = gtdUserDao;
    }


}
