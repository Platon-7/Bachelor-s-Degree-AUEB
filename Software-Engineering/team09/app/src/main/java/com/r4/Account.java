package com.r4;

public class Account {

    String AM = null;
    String password = null;
    String email = null;

    public Account(Student student){
        this.AM = student.getAM();
        this.password = student.getPassword();
        this.email = student.getEmail();
    }

    public String getAM() {
        return AM;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
