package com.memorydao;

import com.dao.ProjectDAO;
import com.r4.Project;

import java.security.Policy;
import java.util.ArrayList;
import java.util.List;

public class ProjectMemory implements ProjectDAO {
    static List<Project> projects=new ArrayList<>();


    @Override
    public void saveProject(Project project) {
        if(!projects.contains(projects)){
            projects.add(project);
        }
    }

    @Override
    public List<Project> getAvailableProjects() {
        return projects;
    }

    @Override
    public Project findProject(String course) {
        for (int i=0;i<projects.size();i++){
            if(projects.get(i).getCourse().getTitle().equals(course))
                return projects.get(i);
        }
        return null;
    }

    public boolean checkProject(Project project){
        for(int i=0;i<projects.size();i++){
            if(projects.get(i).getCourse().getTitle().equals(project.getCourse().getTitle()) &&
                    projects.get(i).getMaxNumber()==project.getMaxNumber()&&
                    projects.get(i).getDeadline().equals(project.getDeadline())){
                return false;
            }
        }
        return true;
    }

}
