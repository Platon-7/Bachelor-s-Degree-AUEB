package com.r4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Toast;

import com.dao.StudentDAO;
import com.dao.TeamDAO;
import com.memorydao.StudentMemory;
import com.memorydao.TeamMemory;

import java.util.List;

public class TeamOverview extends AppCompatActivity implements StudentFragment.OnStudentListener {
    private TeamDAO teamDAO;
    private StudentDAO studentDAO;
    private String user;
    private String course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = intent.getStringExtra(Menu.CURRENT_USER_AM);
        course = intent.getStringExtra(Menu.COURSE_SEARCH);
        setContentView(R.layout.activity_team_overview);
        setTitle("My team Members");
        teamDAO=new TeamMemory();
        studentDAO=new StudentMemory();

    }


    @Override
    public void EvaluateStudent(Student student, int evaluation) {
        if (evaluation >= 0 && evaluation <= 5) {
            if (studentDAO.findStudent(user).checkEvaluation(student, teamDAO.findTeam(course))) {
                if (studentDAO.findStudent(user).evaluate(evaluation, student, teamDAO.findTeam(course))) {
                    Toast.makeText(getApplicationContext(), "Evaluation successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You have to wait after the deadline", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Already evaluated", Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(getApplicationContext(), "Evaluation must be from 0 to 5", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public List<Student> getStudents() {
        return teamDAO.myTeamMembers(studentDAO.findStudent(user).getTeams(),course,user);
    }
}