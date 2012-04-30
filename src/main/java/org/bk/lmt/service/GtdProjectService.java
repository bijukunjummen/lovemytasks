package org.bk.lmt.service;

import java.util.List;

import org.bk.lmt.domain.GtdProject;

public interface GtdProjectService {
    GtdProject persistForUser(GtdProject gtdProject, String userName);
    GtdProject findById(Long id);
    List<GtdProject> findGTDProjectsByGtdUser(String userName, int firstResult, int maxResults);
    List<GtdProject> findGTDProjectsByGtdUser(String userName);
    Long countProjectsByUserName(String userName);
    GtdProject updateForUser(GtdProject gtdProject, String userName);
    void remove(GtdProject gtdProject);
}
