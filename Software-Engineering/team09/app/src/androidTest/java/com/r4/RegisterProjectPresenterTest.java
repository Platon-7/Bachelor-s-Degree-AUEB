package com.r4;


import com.dao.AccountDAO;
import com.dao.CourseDAO;
import com.dao.Initializer;
import com.dao.ProjectDAO;
import com.dao.StudentDAO;
import com.memorydao.AccountMemory;
import com.memorydao.CourseMemory;
import com.memorydao.InitializerMemory;
import com.memorydao.ProjectMemory;
import com.memorydao.StudentMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegisterProjectPresenterTest{
    private RegisterProjectViewStub view;
    private RegisterProjectPresenter presenter;

    @Before
    public void setup(){
        Initializer initializer = new InitializerMemory();
        initializer.prepareData();
        view = new RegisterProjectViewStub();
        RegisterProjectViewModel viewmodel = new RegisterProjectViewModel();
        presenter = viewmodel.getPresenter();
        presenter.setView(view);
    }
    @Test
    public void createProjectFail(){
        presenter.createProject("Databases","3","06/06/2021");
        Assert.assertEquals("Project already exists",view.getShowError());
    }
    @Test
    public void createProjectSuccess(){
        presenter.createProject("Mathimatika","3","06/06/2021");
        Assert.assertEquals(0,view.getErrorCount());
    }

}