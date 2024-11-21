package com.r4;



import com.dao.CourseDAO;

public class MenuPresenter {
    private MenuView view;
    private CourseDAO courseDAO;
    public MenuPresenter() {

    }

    public void showResults(String course,String user) {
        if(course.isEmpty()){
            view.showError("Course cannot be null");
        }else
        {
            if(courseDAO.findCourse(course)) view.showResults(course,user);
            else{
                view.showErrorCourse("Course may not have project or else check your input...");
            }
        }
    }
    public void editInfo() {
        view.editInfo();
    }
    public void myTeams(){
        view.myTeams();
    }

    public void createTeam(){
        view.createTeam();
    }

    public void showMessages(String user) {
        view.showMessages(user);
    }

    public void setView(MenuView view) {
        this.view = view;
    }

    public void setCourseDAO(CourseDAO courseDAO){
        this.courseDAO = courseDAO;
    }
}
