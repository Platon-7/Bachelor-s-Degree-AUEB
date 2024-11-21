package com.r4;

import com.dao.AccountDAO;

public class LoginPresenter {
    private LoginView view;
    private AccountDAO accountDAO;
    public LoginPresenter() {
    }


    public void login(String AM,String password){
        if(accountDAO.adminCheck(AM,password)){
            view.showRegisterScreen();
        }else {
            if (accountDAO.loginCheck(AM, password)) {
                view.showMenu(AM);
            } else {
                view.ShowError("Account not found with those credentials");
            }
        }
    }

    public void setView(LoginView view) {
        this.view = view;
    }

    public void signUp(){
        view.signUp();
    }
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }
}
