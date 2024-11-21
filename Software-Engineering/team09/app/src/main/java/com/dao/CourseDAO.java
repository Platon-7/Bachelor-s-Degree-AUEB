package com.dao;

import com.r4.Course;


import java.util.List;

public interface CourseDAO {


    public void saveCourse(Course course);
    public boolean findCourse(String course);

    List<Course> getAvailableCourses();

}
