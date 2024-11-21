package com.r4;

import com.dao.AccountDAO;
import com.dao.StudentDAO;


public class EditInfoPresenter {
    private EditInfoView view;
    private AccountDAO accountDAO;
    private StudentDAO studentDAO;
    public EditInfoPresenter() {

    }
    public void changePassword(String user,String oldpassword,String newpassword,String repeatpassword){
        if(accountDAO.loginCheck(user,oldpassword)){
            if(accountDAO.checkPasswordValidity(newpassword)){
                if(accountDAO.checkPasswordEquality(newpassword,repeatpassword)){
                    accountDAO.findAccount(user).setPassword(newpassword);
                    studentDAO.findStudent(user).setPassword(newpassword);
                    view.showSuccessfulChange("Password changed successfully");
                }
                else{
                    view.showErrorPasswordEquality("Passwords do not match");
                }
            }else{
                view.showErrorSize("The password must be between 6-15 characters...");
            }

        }else{
            view.showErrorPassword("Incorrect password");
        }

    }
    public void updateSkills(String user,String skills){
        if(skills.isEmpty()){
            view.showErrorNullSkills("Field skills cannot be null");
        }else{
            studentDAO.findStudent(user).setSkills(skills);
            view.showSuccessfulUpdateSkills("Skills successfully updated");
        }

    }
    public void updateTime(String user,String time){
        if(time.isEmpty()){
            view.showErrorNullTime("Field time cannot be null");
        }else{
            studentDAO.findStudent(user).setTimeline(time);
            view.showSuccessfulUpdateTime("Timeline successfully updated");
        }

    }

    public void setView(EditInfoView view) {
        this.view = view;
    }

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
}
