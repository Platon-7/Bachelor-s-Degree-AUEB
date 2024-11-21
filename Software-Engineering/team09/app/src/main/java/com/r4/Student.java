package com.r4;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Student {

    String name = null;
    String surname = null;
    String AM = null;
    String password = null;
    String email = null;
    String skills = null;
    String timeline;
    ArrayList<Team> teams = new ArrayList<>();
    ArrayList<Request> requests = new ArrayList<>();
    ArrayList<Evaluation> evaluations = new ArrayList<>();
    ArrayList<Evaluation> evaluated = new ArrayList<>();


    public Student() {

    }

    public Student(String name, String surname, String AM, String password, String email, String skills, String timeline) {
        this.name = name;
        this.surname = surname;
        this.AM = AM;
        this.password = password;
        this.email = email;
        this.skills = skills;
        this.timeline = timeline;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAM() {
        return AM;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getSkills() {
        return skills;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public void addTeams(Team team) {
        teams.add(team);
    }

    public boolean evaluate(int evaluation, Student evaluatee,Team team) {
            Date d1 = new Date();
            Date d2 = new Date();

            SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                d1 = sdformat.parse(team.getProject().getDeadline());
                d2 = java.util.Calendar.getInstance().getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (d1.compareTo(d2) < 0) {
                Evaluation eval = new Evaluation(this, evaluatee, evaluation,team);
                evaluations.add(eval);
                evaluatee.getEvaluated().add(eval);
                return true;
            }
            return false;

    }

    public Request sendRequest(Team appl_team) {
        Request req = new Request(this, appl_team);
        requests.add(req);
        return req;
    }

    public Team createTeam(Project project, String requirements) {
        ArrayList<Student> members = new ArrayList<>();
        members.add(this);
        Team team = new Team(project, this, members, requirements);
        teams.add(team);
        return team;
    }

    public void createAccount() {
        Account account = new Account(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void removeRequest(Request request) {
        for(int i=0;i<requests.size();i++){
            if(requests.get(i).getSender().getAM().equals(request.getSender().getAM())
                    &&requests.get(i).getApplicationTeam().getProject().getCourse().getTitle().equals(request.getApplicationTeam().getProject().getCourse().getTitle())){
                requests.remove(request);
                break;
            }
        }
    }
    public boolean checkEvaluation(Student evaluatee,Team team){
        for(int i=0;i<evaluations.size();i++) {
            if (evaluations.get(i).evaluator.getAM().equals(this.getAM()) &&
                    evaluations.get(i).getEvaluatee().getAM().equals(evaluatee.getAM())&&
                    evaluations.get(i).getTeam().getProject().getCourse().getTitle().equals(team.getProject().getCourse().getTitle())){
                    return false;
            }
        }
        return true;
    }
    public ArrayList<Evaluation> getEvaluated() {
        return evaluated;
    }
    public double getTotalEvaluation(){
        int total=0;
        for(int i=0;i<evaluated.size();i++){
            total+=evaluated.get(i).getGrade();
        }
        return (double)total/evaluated.size();
    }
    public ArrayList<Team> getTeams() {
        return teams;
    }
}
