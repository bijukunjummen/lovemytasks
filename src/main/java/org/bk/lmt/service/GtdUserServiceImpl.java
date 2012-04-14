package org.bk.lmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.bk.lmt.dao.GtdUserDao;
import org.bk.lmt.domain.GtdUser;
import org.springframework.stereotype.Service;

@Service
public class GtdUserServiceImpl implements GtdUserService{
	@Resource private GtdUserDao gtdUserDao;
	
	@Override
    public List<GtdUser> findAllGTDUsers(int firstResult, int maxResults) {
	    return this.gtdUserDao.findAllGTDUsers(firstResult, maxResults);
    }
	
	@Override
    public GtdUser findUserByUserName(String userName) {
		return this.gtdUserDao.findUserByUserName(userName);
    }

	public void setGtdUserDao(GtdUserDao gtdUserDao) {
    	this.gtdUserDao = gtdUserDao;
    }

}
