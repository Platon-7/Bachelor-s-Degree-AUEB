package com.r4;

public class Request  {

    Student sender;
    Team application_team;
    int id;
    static boolean status = true;

    public Request(Student sender, Team team){
        this.sender = sender;
        this.application_team = team;

    }

    public Student getSender() {
        return sender;
    }

    public Team getApplicationTeam() {
        return application_team;
    }



    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

}
