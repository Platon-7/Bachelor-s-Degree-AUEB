package com.memorydao;

import com.dao.CourseDAO;
import com.r4.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseMemory implements CourseDAO {

    static List<Course> courses=new ArrayList<>();
    @Override
    public void saveCourse(Course course) {
        if(!courses.contains(course)){
            courses.add(course);
        }
    }

    @Override
    public boolean findCourse(String course) {
        for(int i=0;i<courses.size();i++){
            if(courses.get(i).getTitle().equals(course))
                return true;
        }
        return false;
    }

    @Override
    public List<Course> getAvailableCourses() {
        return courses;
    }
}
