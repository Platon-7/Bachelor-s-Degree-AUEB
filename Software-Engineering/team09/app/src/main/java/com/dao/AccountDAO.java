package com.dao;
import com.r4.Account;
import com.r4.Student;

import java.util.List;

public interface AccountDAO {
    public  boolean adminCheck(String AM, String password);
    public  Account findAccount(String user);
    public void setAdminAccount(Account admin);

    public void saveAccount(Account account);

    public boolean loginCheck(String AM, String password);

    public boolean checkPasswordValidity(String password);

    public boolean checkPasswordEquality(String password1, String password2);

    public boolean checkName(String name);

    public boolean checkSurname(String surname);

    public boolean checkAMFormat(String AM);

    public boolean checkAMExistance(String AM);

    public boolean checkEmailFormat(String email);

    public boolean checkEmailExistance(String email);

    public List<Account> getAccounts();
}
