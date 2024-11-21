package com.r4;

public class LoginViewStub implements LoginView{

    int errorcount;
    @Override
    public void signUp() {

    }

    @Override
    public void showMenu(String AM) {

    }

    public void ShowError(String error) {
        errorcount++;
    }

    @Override
    public void showRegisterScreen() {

    }

    public int getErrorCount(){
        return errorcount;
    }
}
