package com.dao;

import com.r4.Student;

import java.util.List;

public interface StudentDAO {


    public void saveStudent(Student student);

    public List<Student> getStudents();
    public Student findStudent(String AM);
}
