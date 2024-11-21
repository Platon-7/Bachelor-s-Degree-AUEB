package com.r4;

import androidx.lifecycle.ViewModel;

import com.dao.AccountDAO;
import com.dao.StudentDAO;
import com.memorydao.AccountMemory;
import com.memorydao.StudentMemory;

public class EditInfoViewModel extends ViewModel {

    private EditInfoPresenter presenter;
    public EditInfoViewModel() {
        AccountDAO accountDAO=new AccountMemory();
        StudentDAO studentDAO=new StudentMemory();
        presenter=new EditInfoPresenter();
        presenter.setAccountDAO(accountDAO);
        presenter.setStudentDAO(studentDAO);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public EditInfoPresenter getPresenter() {
        return presenter;
    }
}
