package org.bk.lmt.dao;

import java.util.List;

import org.bk.lmt.domain.GtdProject;

public interface GtdProjectDao extends BaseDao<Long, GtdProject>{
	List<GtdProject> findGTDProjectsByGtdUser(String userName, int firstResult, int maxResults);
    Long countProjectsByUserName(String userName);
}
