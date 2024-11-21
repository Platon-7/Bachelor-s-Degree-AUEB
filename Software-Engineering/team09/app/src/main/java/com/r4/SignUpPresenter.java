package com.r4;

import com.dao.AccountDAO;
import com.dao.StudentDAO;

public class SignUpPresenter {

    private SignUpView view;
    private AccountDAO accountDAO;
    private StudentDAO studentDAO;
    public SignUpPresenter() {

    }


    public void signUp(String input_AM, String input_password1, String input_password2, String input_email, String input_name, String input_surname, String input_skills, String input_timeline ){
        int conditions = 0;
        if(accountDAO.checkAMExistance(input_AM)){
            conditions++;
        }else{
          view.showError("The AM you entered already exists...");
        }
        if(accountDAO.checkAMFormat(input_AM)){
            conditions++;
        }else{
            view.showError("Please check the AM...");
        }
        if(accountDAO.checkName(input_name)){
            conditions++;
        }else{
            view.showError("Name can not be null...");
        }
        if(accountDAO.checkSurname(input_surname)){
            conditions++;
        }else{
            view.showError("Surname can not be null...");
        }
        if(accountDAO.checkEmailExistance(input_email)){
            conditions++;
        }else{
            view.showError("The email you entered already exists...");
        }
        if(accountDAO.checkEmailFormat(input_email)){
            conditions++;
        }else{
            view.showError("Incorrect email format");
        }
        if(accountDAO.checkPasswordValidity(input_password1) ){
            conditions++;
        }else{
            view.showError("The password must be between 6-15 characters...");
        }
        if(accountDAO.checkPasswordEquality(input_password1, input_password2)){
            conditions++;
        }else{
            view.showError("The passwords you entered do not match...");
        }
        if(conditions==8){
            String skills=input_skills;
            String timeline=input_timeline;
            if(input_skills.isEmpty()){
                skills="not yet filled";
                if(input_timeline.isEmpty()){
                    timeline="not yet filled";
                }
            }
            Student student=new Student(input_name,input_surname,input_AM,input_password1,input_email,skills,timeline);
            Account newAccount=new Account(student);
            studentDAO.saveStudent(student);
            accountDAO.saveAccount(newAccount);
            view.showSuccess("Account successfully created");
            view.showMenu();
        }
    }

    public void setView(SignUpView view) {
        this.view = view;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }
}
