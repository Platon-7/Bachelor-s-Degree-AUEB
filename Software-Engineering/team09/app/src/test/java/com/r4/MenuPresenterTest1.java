package com.r4;

import com.dao.CourseDAO;
import com.dao.Initializer;
import com.memorydao.CourseMemory;
import com.memorydao.InitializerMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MenuPresenterTest1{
    private MenuViewStub view;
    private MenuPresenter presenter;
    @Before
    public void setup(){
        Initializer initializer = new InitializerMemory();
        initializer.prepareData();
        view = new MenuViewStub();
        MenuViewModel viewmodel = new MenuViewModel();
        presenter = viewmodel.getPresenter();
        presenter.setView(view);
    }
    @Test
    public void doNotShowResults(){
        presenter.showResults("","p3180068");
        Assert.assertEquals(1,view.getErrorCount());
    }
    @Test
    public void showResults(){
        presenter.showResults("Distributed Systems","p3180068");
        Assert.assertEquals(0,view.getErrorCount());
    }
    @Test
    public void doNotShowResults_2(){
        presenter.showResults("Klaketes","p3180068");
        Assert.assertEquals("Course may not have project or else check your input...",view.getErrorCourse());
    }
    @Test
    public void myTeams(){
        presenter.myTeams();
        Assert.assertEquals(0,view.getErrorCount());
    }
    @Test
    public void createTeam(){
        presenter.createTeam();
        Assert.assertEquals(0,view.getErrorCount());
    }
    @Test
    public void showMessages(){
        presenter.showMessages("p3180068");
        Assert.assertEquals(0,view.getErrorCount());
    }
    @Test
    public void editInfo(){
        presenter.editInfo();
        Assert.assertEquals(0,view.getErrorCount());
    }

}