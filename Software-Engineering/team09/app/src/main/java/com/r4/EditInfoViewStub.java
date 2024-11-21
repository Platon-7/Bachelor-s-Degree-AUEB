package com.r4;

public class EditInfoViewStub implements EditInfoView{
    String error;
    int errorcount;

    public String getErrorPassword(){
        return error;
    }
    public int getErrorCount(){
        return errorcount;
    }
    @Override
    public void showErrorPassword(String error_pas) {
        errorcount++;
        error = error_pas;
    }

    public String getErrorSize(){
        return error;
    }
    @Override
    public void showErrorSize(String error_pas) {
        errorcount++;
        error = error_pas;
    }

    public String getErrorPasswordEquality(){
        return error;
    }
    @Override
    public void showErrorPasswordEquality(String error_pas) {
        errorcount++;
        error = error_pas;
    }

    public String getErrorNullSkills(){
        return error;
    }
    @Override
    public void showErrorNullSkills(String error_skills) {
        errorcount++;
        error = error_skills;
    }

    public String getErrorNullTime(){
        return error;
    }

    @Override
    public void showErrorNullTime(String error_time) {
        errorcount++;
        error = error_time;
    }

    @Override
    public void showSuccessfulChange(String success) {

    }

    @Override
    public void showSuccessfulUpdateSkills(String success) {

    }

    @Override
    public void showSuccessfulUpdateTime(String success) {

    }
}
