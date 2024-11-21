package com.r4;

import androidx.lifecycle.ViewModel;

import com.dao.CourseDAO;
import com.dao.ProjectDAO;
import com.dao.StudentDAO;
import com.dao.TeamDAO;
import com.memorydao.CourseMemory;
import com.memorydao.ProjectMemory;
import com.memorydao.StudentMemory;
import com.memorydao.TeamMemory;

public class CreateTeamViewModel extends ViewModel {
    private  CreateTeamPresenter presenter;
    public CreateTeamViewModel() {

        presenter=new CreateTeamPresenter();
        CourseDAO courseDAO=new CourseMemory();
        StudentDAO studentDAO=new StudentMemory();
        TeamDAO teamDAO =new TeamMemory();
        ProjectDAO projectDAO=new ProjectMemory();
        presenter.setCourseDAO(courseDAO);
        presenter.setProjectDAO(projectDAO);
        presenter.setStudentDAO(studentDAO);
        presenter.setTeamDAO(teamDAO);


    }

    public CreateTeamPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
