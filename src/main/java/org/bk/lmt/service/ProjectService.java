package org.bk.lmt.service;

import java.util.List;

import org.bk.lmt.domain.Project;

public interface ProjectService {
    Project persistForUser(Project gtdProject, String userName);
    Project findById(Long id);
    List<Project> findProjectsByGtdUser(String userName, int firstResult, int maxResults);
    List<Project> findProjectsByGtdUser(String userName);
    Long countProjectsByUserName(String userName);
    Project updateForUser(Project gtdProject, String userName);
    void remove(Project gtdProject);
}
