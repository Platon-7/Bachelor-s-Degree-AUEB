package com.r4;

import androidx.lifecycle.ViewModel;

import com.dao.AccountDAO;
import com.memorydao.AccountMemory;

public class LoginScreenViewModel extends ViewModel {

    private LoginPresenter presenter;


    public LoginScreenViewModel(){
        AccountDAO account=new AccountMemory();
        presenter=new LoginPresenter();
        presenter.setAccountDAO(account);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public LoginPresenter getPresenter() {
        return presenter;
    }
}
