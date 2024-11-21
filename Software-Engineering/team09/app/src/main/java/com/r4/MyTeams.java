package com.r4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dao.StudentDAO;
import com.dao.TeamDAO;
import com.memorydao.StudentMemory;
import com.memorydao.TeamMemory;

import java.util.List;

public class MyTeams extends AppCompatActivity implements MyTeamFragment.OnMyTeamListener {

    private StudentDAO studentDAO;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_teams);
        setTitle("My Teams");
        Intent intent = getIntent();
        user = intent.getStringExtra(Menu.CURRENT_USER_AM);
        studentDAO=new StudentMemory();

    }

    @Override
    public void TeamSelection(Team team) {
        Intent intent = new Intent(this,TeamOverview.class);
        intent.putExtra(Menu.CURRENT_USER_AM,user);
        intent.putExtra(Menu.COURSE_SEARCH,team.getProject().getCourse().getTitle());
        startActivity(intent);
    }

    @Override
    public List<Team> getMyTeams() {
        return studentDAO.findStudent(user).getTeams();
    }
}