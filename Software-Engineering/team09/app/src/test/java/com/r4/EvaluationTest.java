package com.r4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class EvaluationTest {
    Course course = new Course("Techno");
    ArrayList<Student> members = new ArrayList<>();
    Student sender = new Student("Zinedine", "Zidane", "p3180060", "halamadrid", "zinedine@gmail.com", null, null);
    Student founder = new Student("Cristian", "Karempe", "p3180266", "karempepaiktara", "karempe@gmail.com", null, null);
    Project project = new Project(course, 3, "06/06/2021");
    Student evaluator = new Student("Cristiano", "Ronaldo", "p3180068", "cr7forthewin", "cr7@gmail.com", null, null);
    Student evaluatee = new Student("Antelino", "Vierinha", "p3190068", "mordorpresident", "ork@ork.ork", null, null);
    ArrayList<Evaluation> evaluations = new ArrayList<>();
    ArrayList<Team> teams = new ArrayList<>();
    ArrayList<Request> requests = new ArrayList<>();
    Team team = new Team(project, founder, members, "No requirements");
    Evaluation evaluate = new Evaluation(evaluator,evaluatee,3,team);

    @Before
    public void setUp() {
        members.add(evaluator);
        members.add(evaluatee);
    }
    @Test
    public void getEvaluatee(){
        Assert.assertEquals(evaluatee.getAM() ,evaluate.getEvaluatee().getAM());
    }
    @Test
    public void getEvaluator(){
        Assert.assertEquals(evaluator.getAM() ,evaluate.getEvaluator().getAM());
    }
    @Test
    public void getGrade(){
        Assert.assertEquals(3,evaluate.getGrade());
    }
    @Test
    public void getTeam(){
        Assert.assertEquals(team,evaluate.getTeam());
    }
    @Test
    public void setGrade(){
        evaluate.setGrade(5);
        Assert.assertEquals(5,evaluate.getGrade());

    }

}
