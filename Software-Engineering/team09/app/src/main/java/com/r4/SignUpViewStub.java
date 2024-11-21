package com.r4;

public class SignUpViewStub implements SignUpView{
    int errorcount;
    String errorMessage;
    @Override
    public void showMenu() {

    }

    @Override
    public void showError(String error) {
        if(error.equals("The AM you entered already exists...")){
            showAMexists("The AM you entered already exists...");
            errorcount++;
        }
        if(error.equals("Please check the AM...")){
            showWrongAM("Please check the AM...");
            errorcount++;
        }
        if(error.equals("Name can not be null...")){
            setNameError("Name can not be null...");
            errorcount++;
        }
        if(error.equals("Surname can not be null...")){
            setSurnameError("Surname can not be null...");
            errorcount++;
        }
        if(error.equals("The email you entered already exists...")){
            setEmailError("The email you entered already exists...");
            errorcount++;
        }
        if(error.equals("Incorrect email format")){
            setEmailFormatError("Incorrect email format");
            errorcount++;
        }
        if(error.equals("The password must be between 6-15 characters...")){
            setCriteriaError("The password must be between 6-15 characters...");
            errorcount++;
        }
        if(error.equals("The passwords you entered do not match...")){
            setMatchingError("The passwords you entered do not match...");
            errorcount++;
        }
    }
    public void setMatchingError(String s){
        errorMessage = s;
    }
    public String matchingError(){
        return errorMessage;
    }
    public String criteriaError(){
        return errorMessage;
    }
    public void setCriteriaError(String s){
        errorMessage = s;
    }
    public String emailFormatError(){
        return errorMessage;
    }
    public void setEmailFormatError(String s){
        errorMessage = s;
    }
    public String emailError(){
        return errorMessage;
    }
    public void setEmailError(String s){
        errorMessage = s;
    }
    public String surnameError(){
        return errorMessage;
    }
    public void setSurnameError(String s){
        errorMessage = s;
    }
    public String nameError(){
        return errorMessage;
    }
    public void setNameError(String s){
        errorMessage = s;
    }
    @Override
    public void showSuccess(String account_successfully_created) {

    }
    public int getErrorCount(){
        return errorcount;
    }

    public String AMexists(){
        return errorMessage;
    }
    public void showAMexists(String s){
        errorMessage = s;
    }
    public String wrongAM(){
        return errorMessage;
    }
    public void showWrongAM(String s){
        errorMessage = s;
    }

}
