package org.bk.lmt.service;

import java.util.List;

import org.bk.lmt.domain.GtdUser;

public interface GtdUserService {
	List<GtdUser> findAllGTDUsers(int firstResult, int maxResults);
	GtdUser findUserByUserName(String userName);
}
