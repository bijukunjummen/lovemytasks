package org.bk.lmt.dao;

import java.util.List;

import org.bk.lmt.domain.GtdUser;

public interface GtdUserDao extends BaseDao<Long, GtdUser> {
	List<GtdUser> findAllGTDUsers(int firstResult, int maxResults);
	GtdUser findUserByUserName(String userName);
}
