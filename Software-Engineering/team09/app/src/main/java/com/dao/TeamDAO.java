package com.dao;

import com.r4.R;
import com.r4.Request;
import com.r4.Student;
import com.r4.Team;

import java.util.List;

public interface TeamDAO {


    public void saveTeam(Team team);

    public boolean findRequest(Team team,Request request);

    public Team findTeam(String course);
    List<Team> getAvailableTeams(List<Team> myteams,String course);
    public boolean checksTeams(List<Team> myteams,String course);
    List<Student> myTeamMembers(List<Team> myteams,String course,String user);


}

