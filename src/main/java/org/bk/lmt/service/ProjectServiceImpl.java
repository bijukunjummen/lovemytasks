package org.bk.lmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.bk.lmt.dao.ProjectDao;
import org.bk.lmt.dao.TaskUserDao;
import org.bk.lmt.domain.Project;
import org.bk.lmt.domain.TaskUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectServiceImpl implements ProjectService {
    
    @Resource private ProjectDao projectDao;
    @Resource private TaskUserDao gtdUserDao;

    @Transactional
    @Override
    public Project persistForUser(Project gtdProject, String userName) {
        TaskUser gtdUser = this.gtdUserDao.findUserByUserName(userName);
        gtdProject.setTaskUser(gtdUser);
        return projectDao.persist(gtdProject);
    }

    @Override
    public Project findById(Long id) {
        return this.projectDao.findById(id);
    }

    @Override
    public List<Project> findProjectsByGtdUser(String userName, int firstResult, int maxResults) {
        return this.projectDao.findProjectsByGtdUser(userName, firstResult, maxResults);
    }

    @Override
    public List<Project> findProjectsByGtdUser(String userName) {
        return this.projectDao.findProjectsByGtdUser(userName);
    }

    @Override
    public Long countProjectsByUserName(String userName) {
        return this.projectDao.countProjectsByUserName(userName);
    }

    @Transactional
    @Override
    public Project updateForUser(Project gtdProject, String userName) {
        TaskUser gtdUser = this.gtdUserDao.findUserByUserName(userName);
        gtdProject.setTaskUser(gtdUser);
        return this.projectDao.update(gtdProject);
    }

    @Transactional
    @Override
    public void remove(Project gtdProject) {
        this.projectDao.remove(gtdProject);
    }

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public void setGtdUserDao(TaskUserDao gtdUserDao) {
        this.gtdUserDao = gtdUserDao;
    }

    
}
