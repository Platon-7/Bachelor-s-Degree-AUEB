package com.r4;


import com.dao.CourseDAO;
import com.dao.Initializer;
import com.dao.ProjectDAO;
import com.dao.StudentDAO;
import com.dao.TeamDAO;
import com.memorydao.CourseMemory;
import com.memorydao.InitializerMemory;
import com.memorydao.ProjectMemory;
import com.memorydao.StudentMemory;
import com.memorydao.TeamMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreateTeamPresenterTest1{
    private CreateTeamViewStub view;
    private CreateTeamPresenter presenter;
    @Before
    public void setup(){
        Initializer initializer = new InitializerMemory();
        initializer.prepareData();
        view = new CreateTeamViewStub();
        CreateTeamViewModel viewmodel = new CreateTeamViewModel();
        presenter = viewmodel.getPresenter();
        presenter.setView(view);
    }
    @Test
    public void invalidCourse(){
        presenter.createTeam("Klaketes", "p3160068","");
        Assert.assertEquals("Course may not have a project or else check your input...",view.getErrorCourse());
    }
    @Test
    public void nullRequirements(){
        presenter.createTeam("Software Engineering", "p3160068","");
        Assert.assertEquals("Requirements cannot be empty...",view.getErrorRequirements());

    }
    @Test
    public void existingTeam(){
        presenter.createTeam("Software Engineering", "p3180068","No requirements");
        Assert.assertEquals("You are already registered with a team for Software Engineering", view.getErrorCheckTeams());
    }
    @Test
    public void teamCreation(){
        presenter.createTeam("Databases", "p3180068","No requirements");
        Assert.assertEquals(0, view.getErrorCount());

    }
}