package com.r4;

public class Evaluation {

    Student evaluator;
    Student evaluatee;
    int grade;
    Team team;


    public Evaluation(Student evaluator, Student evaluatee,int grade,Team team){
        this.evaluatee = evaluatee;
        this.evaluator = evaluator;
        this.grade = grade;
        this.team=team;
    }

    public Student getEvaluator() {
        return evaluator;
    }

    public Student getEvaluatee() {
        return evaluatee;
    }

    public int getGrade(){
        return grade;
    }

    public Team getTeam() {
        return team;
    }

    public void setGrade(int grade){
        this.grade = grade;
    }
}
