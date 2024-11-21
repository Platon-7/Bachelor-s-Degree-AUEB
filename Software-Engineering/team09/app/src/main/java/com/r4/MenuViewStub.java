package com.r4;

public class MenuViewStub implements MenuView{
    int errorcount;
    String errorCourse;
    @Override
    public void showError(String course_cannot_be_null) {
        errorcount++;
    }

    @Override
    public void showResults(String course, String user) {

    }

    @Override
    public void showMessages(String user) {

    }

    @Override
    public void createTeam() {

    }

    @Override
    public void showErrorCourse(String s) {
        errorCourse = s;
    }

    public int getErrorCount(){
        return errorcount;
    }

    public String getErrorCourse(){
        return errorCourse;
    }

    @Override
    public void myTeams() {

    }

    @Override
    public void editInfo() {

    }

}
