package com.r4;

import androidx.lifecycle.ViewModel;

import com.dao.AccountDAO;
import com.dao.StudentDAO;
import com.memorydao.AccountMemory;
import com.memorydao.StudentMemory;

public class SignUpViewModel extends ViewModel {
    private SignUpPresenter presenter;

    public SignUpViewModel() {
        presenter=new SignUpPresenter();
        AccountDAO accountDAO=new AccountMemory();
        presenter.setAccountDAO(accountDAO);
        StudentDAO studentDAO=new StudentMemory();
        presenter.setStudentDAO(studentDAO);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public SignUpPresenter getPresenter() {
        return presenter;
    }
}
