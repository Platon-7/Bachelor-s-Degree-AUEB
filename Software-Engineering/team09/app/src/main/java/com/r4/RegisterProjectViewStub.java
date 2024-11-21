package com.r4;

public class RegisterProjectViewStub implements RegisterProjectView{
    int errorcount;
    String error;

    public int getErrorCount(){
        return errorcount;
    }
    @Override
    public void showSuccess(String project_successfully) {

    }

    @Override
    public void showError(String project_already_exists) {
        error = project_already_exists;
    }
    public String getShowError(){
        errorcount++;
        return error;
    }
}
