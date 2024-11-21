package com.r4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class StudentTest{
    Course course = new Course("Techno");
    ArrayList<Student> members = new ArrayList<>();
    Student sender = new Student("Zinedine","Zidane","p3180060","halamadrid","zinedine@gmail.com",null,null);
    Student founder = new Student("Cristian","Karempe","p3180266","karempepaiktara","karempe@gmail.com",null,null);
    Project project = new Project(course,3,"06/06/2021");
    Student evaluator = new Student("Cristiano","Ronaldo","p3180068","cr7forthewin","cr7@gmail.com",null,null);
    Student evaluatee = new Student("Antelino","Vierinha","p3190068","mordorpresident","ork@ork.ork",null,null);
    ArrayList<Evaluation> evaluations = new ArrayList<>();
    ArrayList<Team> teams = new ArrayList<>();
    ArrayList<Request> requests = new ArrayList<>();
    Team team = new Team(project,founder,members,"No requirements");

    @Before
    public void setUp(){
        members.add(evaluator);
        members.add(evaluatee);
    }

    @Test
    public void Evaluate(){
       // Evaluation evaluation = new Evaluation(evaluator,evaluatee,5);
        evaluator.addTeams(team);
       // evaluator.Evaluate(5,evaluatee,);
      //  Assert.assertEquals(evaluator,evaluation.getEvaluator());
    }
    @Test
    public void getTeams(){
        sender.addTeams(team);
        ArrayList<Team> temp = new ArrayList<>();
        temp.add(team);
        Assert.assertEquals(temp,sender.getTeams());

    }
    @Test
    public void getName(){
        Assert.assertEquals("Zinedine",sender.getName());
    }
    @Test
    public void getSurname(){
        Assert.assertEquals("Zidane",sender.getSurname());
    }
    @Test
    public void getAM(){
        Assert.assertEquals("p3180060",sender.getAM());
    }
    @Test
    public void getEmail(){
        Assert.assertEquals("zinedine@gmail.com",sender.getEmail());
    }
    @Test
    public void getPassword(){
        Assert.assertEquals("halamadrid",sender.getPassword());
    }
    @Test
    public void setName(){
        sender.setName("Zidanaros");
        Assert.assertEquals("Zidanaros",sender.getName());
    }
    @Test
    public void setSurname(){
        sender.setSurname("Zidanaros");
        Assert.assertEquals("Zidanaros",sender.getSurname());
    }
    @Test
    public void createAccount(){
        Account account = new Account(sender);
        sender.createAccount();
        Assert.assertEquals("p3180060", sender.getAM());
    }
    @Test
    public void updateRequests(){
        Request request = new Request(sender,team);
        //sender.updateRequests();
        Assert.assertEquals(0,sender.requests.size());
    }
    @Test
    public void createTeam(){
        boolean flag = false;
        Course course2 = new Course("Katanemhmena");
        Project project2 = new Project(course2,3,"06/06/2021");
        Team cr_team = evaluatee.createTeam(project2,"No requirements");
        ArrayList<Team> temp = evaluatee.getTeams();
        for(int i=0;i<evaluatee.teams.size();i++) {
            if(temp.get(i).equals(cr_team)){
                flag = true;
            }
        }
        Assert.assertEquals(true,flag);
    }
    @Test
    public void getTotalEvaluation(){
        Assert.assertEquals(0,(int)evaluator.getTotalEvaluation());
    }
    @Test
    public void sendRequest(){
        boolean flag = false;
        Request req = sender.sendRequest(team);
        ArrayList<Request> temp = sender.requests;
        for(int i = 0; i< sender.requests.size();i++) {
            if(temp.get(i).equals(req)){
                flag = true;
            }
        }
        Assert.assertEquals(true,flag);
    }
    @Test
    public void getSkills(){

        //Assert.assertEquals("Paiktaras", onemore.getSkills());
    }
    @Test
    public void getTimeline(){

        //Assert.assertEquals("18:00",temp.get(0));
    }
    @Test
    public void setSkills(){
       Student onemore = new Student("Yann","Mvila","p3180267","paiktaraemvila","mvila@gmail.com","many","anytime");
       onemore.setSkills("notmany");
       Assert.assertEquals("notmany",onemore.getSkills());
    }
    @Test
    public void setTimeline(){
        Student onemore = new Student("Yann","Mvila","p3180267","paiktaraemvila","mvila@gmail.com","many","anytime");
        onemore.setTimeline("never");
        Assert.assertEquals("never",onemore.getTimeline());
    }
    @Test
    public void setPassword(){
        sender.setPassword("changed");
        Assert.assertEquals("changed",sender.getPassword());
    }
    @Test
    public void checkEvaluation(){
        Assert.assertEquals(true,evaluator.checkEvaluation(evaluatee,team));
    }


}