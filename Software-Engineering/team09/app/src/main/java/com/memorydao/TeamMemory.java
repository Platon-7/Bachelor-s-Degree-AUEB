package com.memorydao;

import android.widget.Toast;

import com.dao.StudentDAO;
import com.dao.TeamDAO;
import com.r4.Request;
import com.r4.Student;
import com.r4.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamMemory implements TeamDAO {

    static List<Team> teams = new ArrayList<>();

    @Override
    public void saveTeam(Team team) {
        if(!teams.contains(team)){
            teams.add(team);
        }
    }

    @Override
    public boolean findRequest(Team team,Request request) {
        for(int j=0;j<teams.size();j++)
        {
           for(int i=0;i<teams.get(j).getRequests().size();i++)
                if(teams.get(j).getRequests().get(i).getSender().getAM().equals(request.getSender().getAM())) {
                    return true;
                }
        }
        return false;
    }

    @Override
    public Team findTeam(String course) {
        for(int i=0;i<teams.size();i++){
            if(teams.get(i).getProject().getCourse().getTitle().equals(course)){
                return teams.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Team> getAvailableTeams(List<Team> myteams,String course) {
        List<Team> list = teams;
        list.removeAll(myteams);
        List<Team> available=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getProject().getCourse().getTitle().equals(course)) {
                    available.add(list.get(i));
                }
            }
        return available;
    }


    @Override
    public boolean checksTeams(List<Team> myteams, String course) {
        for (int i = 0; i < myteams.size(); i++) {
            if (myteams.get(i).getProject().getCourse().getTitle().equals(course)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Student> myTeamMembers(List<Team> myteams, String course,String user) {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < myteams.size(); i++) {
            if(myteams.get(i).getProject().getCourse().getTitle().equals(course)) {
                for (int j = 0; j < myteams.get(i).getMembers().size(); j++) {
                    if (!myteams.get(i).getMembers().get(j).getAM().equals((user))) {
                        list.add(myteams.get(i).getMembers().get(j));
                    }
                }
            }
        }
        return list;
    }
}
