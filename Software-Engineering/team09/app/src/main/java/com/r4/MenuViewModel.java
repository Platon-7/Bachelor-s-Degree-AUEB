package com.r4;

import androidx.lifecycle.ViewModel;

import com.dao.CourseDAO;
import com.memorydao.CourseMemory;

public class MenuViewModel extends ViewModel {
    private  MenuPresenter presenter;
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public MenuViewModel() {
        presenter=new MenuPresenter();
        CourseDAO course = new CourseMemory();
        presenter.setCourseDAO(course);
    }

    public MenuPresenter getPresenter() {
        return presenter;
    }
}
