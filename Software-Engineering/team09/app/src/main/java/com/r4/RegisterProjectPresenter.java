package com.r4;

import com.dao.CourseDAO;
import com.dao.ProjectDAO;

public class RegisterProjectPresenter {
    private RegisterProjectView view;
    private CourseDAO courseDAO;
    private ProjectDAO projectDAO;

    public RegisterProjectPresenter(){

    }


    public void createProject(String course,String limit,String deadline){
        Course course1=new Course(course);
        Project project=new Project(course1,Integer.valueOf(limit),deadline);
        if(!projectDAO.checkProject(project)){
            courseDAO.saveCourse(course1);
            projectDAO.saveProject(project);
            view.showSuccess("Project successfully registered");

        }else{
            view.showError("Project already exists");
        }
    }

    public void setView(RegisterProjectView view) {
        this.view = view;
    }

    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void setProjectDAO(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }
}
