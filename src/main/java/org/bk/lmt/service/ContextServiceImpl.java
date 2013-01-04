package org.bk.lmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.bk.lmt.dao.TaskUserDao;
import org.bk.lmt.domain.Context;
import org.bk.lmt.domain.TaskUser;
import org.bk.lmt.repository.ContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContextServiceImpl implements ContextService{

	@Autowired private ContextRepository contextRepository;
	@Resource private TaskUserDao gtdUserDao;
	

	@Override
	@Transactional
    public Context persistForUser(Context gtdContext, String userName) {
		TaskUser taskUser = this.gtdUserDao.findUserByUserName(userName);
		gtdContext.setTaskUser(taskUser);
	    return this.contextRepository.save(gtdContext);
    }	
	

	@Override
    public Context findById(Long id) {
		return this.contextRepository.findOne(id);
    }


	@Override
    public Page<Context> findContextsByGtdUserName(String userName, int page, int size) {
		return this.contextRepository.findByUser(userName, new PageRequest(page, size));  
	}
	
	@Override
    public List<Context> findContextsByGtdUserName(String userName) {
		return this.contextRepository.findByUser(userName);
	}
	@Override
	public Long countContextsByUserName(String userName){
		return this.contextRepository.countByUser(userName);
	}

	@Override
	@Transactional
    public Context updateForUser(Context gtdContext, String userName) {
		TaskUser gtdUser = this.gtdUserDao.findUserByUserName(userName);
		gtdContext.setTaskUser(gtdUser);		
		return this.contextRepository.save(gtdContext);
    }

	@Override
	@Transactional
    public void remove(Context context) {
	    this.contextRepository.delete(context);
	    
    }


	public void setGtdUserDao(TaskUserDao gtdUserDao) {
    	this.gtdUserDao = gtdUserDao;
    }


}
