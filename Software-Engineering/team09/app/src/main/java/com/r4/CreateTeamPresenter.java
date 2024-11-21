package com.r4;

import android.util.Log;

import com.dao.CourseDAO;
import com.dao.ProjectDAO;
import com.dao.StudentDAO;
import com.dao.TeamDAO;
import com.memorydao.CourseMemory;

import java.util.ArrayList;
import java.util.List;

public class CreateTeamPresenter {
    private CreateTeamView view;
    private CourseDAO courseDAO;
    private ProjectDAO projectDAO;
    private StudentDAO studentDAO;
    private TeamDAO teamDAO;
    public CreateTeamPresenter() {

    }

    public void setView(CreateTeamView view) {
        this.view = view;
    }

    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void setProjectDAO(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public void createTeam(String course, String user, String requirements){
        if(courseDAO.findCourse(course)){
            if(!requirements.equals("")) {
                if (!teamDAO.checksTeams(studentDAO.findStudent(user).getTeams(), course)) {
                    ArrayList<Student> members = new ArrayList<>();
                    members.add(studentDAO.findStudent(user));
                    Team newTeam = new Team(projectDAO.findProject(course), studentDAO.findStudent(user), members, requirements);
                    studentDAO.findStudent(user).addTeams(newTeam);
                        teamDAO.saveTeam(newTeam);
                    view.showSuccess("Team creation was successful");
                } else {
                        view.showErrorCheckTeams("You are already registered with a team for "+course);
                }
            }else{
                view.showErrorRequirements("Requirements cannot be empty...");
            }

        }else{
            view.showErrorCourse("Course may not have a project or else check your input...");
        }

    }

}
