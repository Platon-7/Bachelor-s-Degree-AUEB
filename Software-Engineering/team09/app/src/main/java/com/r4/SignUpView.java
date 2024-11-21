package com.r4;

public interface SignUpView {
    public void showMenu();
    public void showError(String error);

    void showSuccess(String account_successfully_created);
}
