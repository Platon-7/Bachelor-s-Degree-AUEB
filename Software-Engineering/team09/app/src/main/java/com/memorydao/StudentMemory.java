package com.memorydao;

import com.dao.StudentDAO;
import com.r4.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentMemory implements StudentDAO {

    static  ArrayList<Student> students = new ArrayList<>();


    @Override
    public void saveStudent(Student student) {
        if(!students.contains(student)){
            students.add(student);
        }
    }


    @Override
    public List<Student> getStudents() {
        return students;
    }

    @Override
    public Student findStudent(String AM) {
        for(int i=0;i<students.size();i++){
            if(students.get(i).getAM().equals(AM))
                return students.get(i);
        }
        return null;
    }
}
