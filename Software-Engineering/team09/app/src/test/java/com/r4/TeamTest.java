package com.r4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TeamTest{
    Course course = new Course("Techno");
    ArrayList<Student> members = new ArrayList<>();
    Student sender = new Student("Cristiano","Ronaldo","p3180068","cr7forthewin","cr7@gmail.com",null,null);
    Student founder = new Student("Cristian","Karempe","p3180266","karempepaiktara","karempe@gmail.com",null,null);
    Project project = new Project(course,3,"06/06/2021");
    Team team = new Team(project,founder,members,"No requirements");

    @Before
    public void setUp(){
        members.add(founder);
        members.add(sender);
    }

    @Test
    public void getProject(){
        Assert.assertEquals(project,team.getProject());
    }
    @Test
    public void getFounder(){
        Assert.assertEquals(founder,team.getFounder());
    }
    @Test
    public void getMembers(){
        Assert.assertEquals(members,team.getMembers());
    }
    @Test
    public void getRequirements(){
        Assert.assertEquals("No requirements" , team.getRequirements());
    }
    @Test
    public void addRequests(){
        Request request = new Request(sender,team);
        team.addRequests(request);
        Assert.assertEquals(request,team.requests.get(0));
    }
    @Test
    public void acceptRequest(){
        Student newone = new Student("Lionel","Messi","p3152631","lionelthrilos","leo@gmail.com",null,null);
        Request request = new Request(newone,team);
        team.acceptRequest(request);
        members.add(newone);
        Assert.assertEquals(members,team.getMembers());
    }
    @Test
    public void declineRequest(){
        Student newone = new Student("Lionel","Messi","p3152631","lionelthrilos","leo@gmail.com",null,null);
        Request request = new Request(newone,team);
        team.declineRequest(request);
        Assert.assertEquals(false,request.getStatus());

    }
    @Test
    public void checkTeamSize(){//an thelw na ginei to anapodo check bazw maxsize = 2 kai dhmioyrgw eswterika ena temp arraylist
        Student newone = new Student("Lionel","Messi","p3152631","lionelthrilos","leo@gmail.com",null,null);
        Request request = new Request(newone,team);
        team.acceptRequest(request);
        members.add(newone);
        Assert.assertEquals(members,team.getMembers());
    }
    @Test
    public void getRequests(){
        Student newone = new Student("Lionel","Messi","p3152631","lionelthrilos","leo@gmail.com",null,null);
        Request request = new Request(newone,team);
        team.addRequests(request);
        Assert.assertEquals(request,team.getRequests().get(0));
    }

}