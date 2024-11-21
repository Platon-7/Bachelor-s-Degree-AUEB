package com.r4;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class RequestTest {
    Course course = new Course("Techno");
    ArrayList<Student> members = new ArrayList<>();
    Student sender = new Student("Cristiano","Ronaldo","p3180068","cr7forthewin","cr7@gmail.com",null,null);
    Student founder = new Student("Cristian","Karempe","p3180266","karempepaiktara","karempe@gmail.com",null,null);
    Project project = new Project(course,3,"06/06/2021");
    Team application_team = new Team(project,founder,members,"No requirements");
    Request request = new Request(sender,application_team);

    @Test
    public void getSender(){
        Assert.assertEquals(sender,request.getSender());
    }
    @Test
    public void getApplicationTeam(){
        Assert.assertEquals(application_team,request.getApplicationTeam());
    }
    @Test
    public void getStatus(){
        Assert.assertEquals(false,request.getStatus());
    }
    @Test
    public void setStatus(){
        request.setStatus(false);
        Assert.assertEquals(false,request.getStatus());
    }

}