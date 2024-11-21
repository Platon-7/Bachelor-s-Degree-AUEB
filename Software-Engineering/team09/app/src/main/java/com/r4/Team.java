package com.r4;

import java.util.ArrayList;

public class Team {

    Project project;
    Student founder = new Student();
    String requirements = null;
    ArrayList<Student> members;
    ArrayList<Request> requests = new ArrayList<>();
    public Team(Project project,Student founder, ArrayList<Student> members, String requirements){
        this.project = project;
        this.requirements = requirements;
        this.members = members;
        this.founder = founder;
    }

    public Project getProject() {
        return project;
    }

    public Student getFounder() {
        return founder;
    }

    public String getRequirements() {
        return requirements;
    }

    public ArrayList<Student> getMembers() {
        return members;
    }

    public void addRequests(Request request){
        requests.add(request);
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public boolean acceptRequest(Request request){

            if (checkTeamSize()) {
                members.add(request.getSender());
                request.getSender().getTeams().add(this);
                request.getSender().removeRequest(request);
                request.setStatus(false);
                return true;
            }
        return false;
    }
    public boolean declineRequest(Request request){
        request.getSender().removeRequest(request);
        request.setStatus(false);
        return false;
    }
    public boolean checkTeamSize(){
        if(members.size()<project.getMaxNumber()) {
            return true;
        }
        for(int i = 0; i< requests.size();i++){
            requests.get(i).setStatus(false);
        }
        requests.removeAll(requests);
        return false;
    }
}
