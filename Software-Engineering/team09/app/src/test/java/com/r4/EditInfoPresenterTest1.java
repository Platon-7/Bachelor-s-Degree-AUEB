package com.r4;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dao.AccountDAO;
import com.dao.Initializer;
import com.dao.StudentDAO;
import com.memorydao.AccountMemory;
import com.memorydao.InitializerMemory;
import com.memorydao.StudentMemory;
import com.memorydao.TeamMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EditInfoPresenterTest1 {
    private EditInfoViewStub view;
    private EditInfoPresenter presenter;

    @Before
    public void setup() {
        Initializer initializer = new InitializerMemory();
        initializer.prepareData();
        view = new EditInfoViewStub();
        EditInfoViewModel viewmodel = new EditInfoViewModel();
        presenter = viewmodel.getPresenter();
        presenter.setView(view);
    }
    @Test
    public void incorrectPassword(){
        presenter.changePassword("p3180068","denthimamai","olympiacos","olympiacos");
        Assert.assertEquals("Incorrect password",view.getErrorPassword());
    }
    @Test
    public void invalidSize(){
        presenter.changePassword("p3180068","olimpiakara","oly","oly");
        Assert.assertEquals("The password must be between 6-15 characters...",view.getErrorSize());
    }
    @Test
    public void notMatching(){
        presenter.changePassword("p3180068","olimpiakara","olympiakos","oly");
        Assert.assertEquals("Passwords do not match",view.getErrorPasswordEquality());
    }
    @Test
    public void passwordChanged(){
        presenter.changePassword("p3180068","olimpiakara","olympiakos","olympiakos");
        Assert.assertEquals(0,view.getErrorCount());
    }
    @Test
    public void updateSkillsSuccess(){
        presenter.updateSkills("p3180068","");
        Assert.assertEquals("Field skills cannot be null",view.getErrorNullSkills());
    }
    @Test
    public void updateSkillsFail(){
        presenter.updateSkills("p3180068","Ta panta ola");
        Assert.assertEquals(0,view.getErrorCount());
    }
    @Test
    public void updateTimeSuccess(){
        presenter.updateTime("p3180068","Den mporw katholoy ase");
        Assert.assertEquals(0,view.getErrorCount());
    }
    @Test
    public void updateTimeFail(){
        presenter.updateTime("p3180068","");
        Assert.assertEquals("Field time cannot be null",view.getErrorNullTime());
    }
}