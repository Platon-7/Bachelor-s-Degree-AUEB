package com.dao;

import com.r4.Project;

import java.util.List;

public interface ProjectDAO {

    public void saveProject(Project project);

    List<Project> getAvailableProjects();

    public Project findProject(String course);

    public boolean checkProject(Project project);

}
