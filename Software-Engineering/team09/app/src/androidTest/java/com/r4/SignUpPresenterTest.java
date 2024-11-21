package com.r4;

import com.dao.AccountDAO;
import com.dao.CourseDAO;
import com.dao.Initializer;
import com.dao.StudentDAO;
import com.memorydao.AccountMemory;
import com.memorydao.CourseMemory;
import com.memorydao.InitializerMemory;
import com.memorydao.StudentMemory;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SignUpPresenterTest {
    private SignUpViewStub view;
    private SignUpPresenter presenter;
    //"p3180068","olympiacos","olympiacos","kar@aueb.gr","Platon","Karageorgis","",""
    @Before
    public void setup(){
        Initializer initializer = new InitializerMemory();
        initializer.prepareData();
        view = new SignUpViewStub();
        SignUpViewModel viewmodel = new SignUpViewModel();
        presenter = viewmodel.getPresenter();
        presenter.setView(view);
    }
    @Test
    public void AMexists(){
        presenter.signUp("p3180068","olympiacos","olympiacos","kar@aueb.gr","Platon","Karageorgis","","");
        Assert.assertEquals("The AM you entered already exists...",view.AMexists());
    }
    @Test
    public void wrongAM(){
        presenter.signUp("p310068","olympiacos","olympiacos","kar@aueb.gr","Platon","Karageorgis","","");
        Assert.assertEquals("Please check the AM...",view.wrongAM());
    }
    @Test
    public void emptyName(){
        presenter.signUp("p3100168","olympiacos","olympiacos","kar@aueb.gr","","Karageorgis","","");
        Assert.assertEquals("Name can not be null...",view.nameError());
    }
    @Test
    public void emptySurname(){
        presenter.signUp("p3100168","olympiacos","olympiacos","kar@aueb.gr","Platon","","","");
        Assert.assertEquals("Surname can not be null...",view.surnameError());
    }
    @Test
    public void emailExists(){
        presenter.signUp("p3100168","olympiacos","olympiacos","karaplato@gmail.com","Platon","Karageorgis","","");
        Assert.assertEquals("The email you entered already exists...",view.emailError());
    }
    @Test
    public void emailFormat(){
        presenter.signUp("p3100168","olympiacos","olympiacos","plato@gmailm","Platon","Karageorgis","","");
        Assert.assertEquals("Incorrect email format",view.emailFormatError());
    }
    @Test
    public void passwordLength(){
        presenter.signUp("p3100168","olym","olym","plato@gmail.com","Platon","Karageorgis","","");
        Assert.assertEquals("The password must be between 6-15 characters...",view.criteriaError());
    }
    @Test
    public void passwordMatching(){
        presenter.signUp("p3100168","olym","olympiacos","plato@gmail.com","Platon","Karageorgis","","");
        Assert.assertEquals("The passwords you entered do not match...",view.matchingError());
    }
    @Test
    public void accountCreated(){
        presenter.signUp("p3100168","olympiacos","olympiacos","plato@gmail.com","Platon","Karageorgis","","");
        Assert.assertEquals(0,view.getErrorCount());
    }

}