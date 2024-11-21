package com.r4;

public class Project {

    int maxnumber;
    String deadline = null;
    Course course;

    public Project(Course course, int maxnumber, String deadline){
        this.deadline = deadline;
        this.maxnumber = maxnumber;
        this.course = course;
    }

    public int getMaxNumber() {
        return maxnumber;
    }

    public String getDeadline() {
        return deadline;
    }

    public Course getCourse(){ return course; }
}
