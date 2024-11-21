package com.r4;

import java.util.ArrayList;

public class Course {
    String title = null;
    ArrayList<Project> projects;
    public Course(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
