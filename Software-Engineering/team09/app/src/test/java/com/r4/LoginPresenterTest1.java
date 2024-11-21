package com.r4;

import com.dao.AccountDAO;
import com.dao.Initializer;
import com.memorydao.AccountMemory;
import com.memorydao.InitializerMemory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginPresenterTest1{

    private LoginViewStub view;
    private LoginPresenter presenter;
    @Before
    public void setup(){
        Initializer initializer = new InitializerMemory();
        initializer.prepareData();
        view = new LoginViewStub();
        LoginScreenViewModel viewmodel = new LoginScreenViewModel();
        presenter = viewmodel.getPresenter();
        presenter.setView(view);
    }

    @Test
    public void successfulLogin(){
        presenter.login("p3180068","olimpiakara");
        Assert.assertEquals(0,view.getErrorCount());
    }
    @Test
    public void loginFailed(){
        presenter.login("p3180068","olympiakos_omadara");
        Assert.assertEquals(1,view.getErrorCount());
    }
    @Test
    public void signUp(){
        presenter.signUp();
        Assert.assertEquals(0, view.getErrorCount());
    }

}