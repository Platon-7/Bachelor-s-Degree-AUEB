package com.r4;

public class CreateTeamViewStub implements CreateTeamView{
    int errorcount;
    String error;

    @Override
    public void showErrorCourse(String outpot) {
        errorcount++;
        error = outpot;
    }
    public String getErrorCourse(){
        return error;
    }
    public String getErrorRequirements(){
        return error;
    }
    public String getErrorCheckTeams(){
        return error;
    }

    @Override
    public void showSuccess(String success) {

    }

    public int getErrorCount(){
        return errorcount;
    }

    @Override
    public void showErrorRequirements(String errorreq) {
        errorcount++;
        error = errorreq;
    }

    @Override
    public void showErrorCheckTeams(String checkTeams) {
        errorcount++;
        error = checkTeams;
    }
}
