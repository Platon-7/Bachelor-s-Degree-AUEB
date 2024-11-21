package com.r4;

import androidx.lifecycle.ViewModel;

import com.dao.CourseDAO;
import com.dao.ProjectDAO;
import com.memorydao.CourseMemory;
import com.memorydao.ProjectMemory;

public class RegisterProjectViewModel extends ViewModel {

    private RegisterProjectPresenter presenter;

    public RegisterProjectViewModel() {
        presenter=new RegisterProjectPresenter();
        CourseDAO courseDAO=new CourseMemory();
        presenter.setCourseDAO(courseDAO);
        ProjectDAO projectDAO=new ProjectMemory();
        presenter.setProjectDAO(projectDAO);

    }

    public RegisterProjectPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
