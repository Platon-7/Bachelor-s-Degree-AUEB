package com.r4;

public interface MenuView {
    void showError(String course_cannot_be_null);

    void showResults(String course,String user);

    void showMessages(String user);

    void createTeam();

    void showErrorCourse(String s);

    void myTeams();

    void editInfo();

}
