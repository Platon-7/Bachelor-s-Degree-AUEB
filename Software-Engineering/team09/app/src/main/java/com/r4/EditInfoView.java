package com.r4;

public interface EditInfoView {

    public void showErrorPassword(String error);
    public void showErrorSize(String error);
    public void showErrorPasswordEquality(String error);
    public void showErrorNullSkills(String error);
    public void showErrorNullTime(String error);
    public void showSuccessfulChange(String success);
    public void showSuccessfulUpdateSkills(String success);
    public void showSuccessfulUpdateTime(String success);
}
