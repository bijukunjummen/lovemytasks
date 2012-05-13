package org.bk.lmt.dao;

import java.util.List;

import org.bk.lmt.domain.Project;

public interface ProjectDao extends BaseDao<Long, Project>{
	List<Project> findProjectsByGtdUser(String userName, int firstResult, int maxResults);
	List<Project> findProjectsByGtdUser(String userName);
    Long countProjectsByUserName(String userName);
}
