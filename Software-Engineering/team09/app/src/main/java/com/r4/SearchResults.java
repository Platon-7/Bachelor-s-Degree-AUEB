package com.r4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.dao.AccountDAO;
import com.dao.RequestDAO;
import com.dao.StudentDAO;
import com.dao.TeamDAO;
import com.memorydao.AccountMemory;
import com.memorydao.RequestMemory;
import com.memorydao.StudentMemory;
import com.memorydao.TeamMemory;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity implements TeamFragment.OnInteractionListener {

    public static final String COURSE_NAME_KEYWORD = "course_name_keyword";
    private TeamDAO teamDAO;
    private StudentDAO studentDAO;
    private RequestDAO requestDAO =new RequestMemory();
    private String user;
    private String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teamDAO=new TeamMemory();
        studentDAO=new StudentMemory();
        Intent intent = getIntent();
        user = intent.getStringExtra(Menu.CURRENT_USER_AM);
        course = intent.getStringExtra(Menu.COURSE_SEARCH);
        setContentView(R.layout.activity_search_results);
        setTitle("Search Results");
    }

    @Override
    public void TeamSelection(Team team) {
        Student student = studentDAO.findStudent(user);
        Request request = student.sendRequest(team);
        if (!teamDAO.findRequest(team,request)) {
            team.addRequests(request);
            requestDAO.saveRequest(request);
            Toast.makeText(getApplicationContext(), "Successfully applied", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Application already made", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public List<Team> getTeams() {
        return teamDAO.getAvailableTeams(studentDAO.findStudent(user).teams,course);
    }
}