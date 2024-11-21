package com.dao;

import android.util.Log;

import com.r4.Account;
import com.r4.Course;
import com.r4.Evaluation;
import com.r4.Project;
import com.r4.Request;
import com.r4.Student;
import com.r4.Team;

import java.util.ArrayList;

public abstract class Initializer {

    public void prepareData() {
        Course soft_eng = new Course("Software Engineering");
        Course dist_systems = new Course("Distributed Systems");
        Course databases = new Course("Databases");
        getCourseDao().saveCourse(databases);
        getCourseDao().saveCourse(soft_eng);
        getCourseDao().saveCourse(dist_systems);

        Project databases_project = new Project(databases, 3, "07/12/2021");
        Project r4 = new Project(soft_eng, 3, "08/06/2021");
        Project tik_tok = new Project(dist_systems, 4, "13/06/2021");
        getProjectDAO().saveProject(r4);
        getProjectDAO().saveProject(tik_tok);
        getProjectDAO().saveProject(databases_project);

        Student kleio = new Student("Kleio", "Patatsi", "p3180266", "monoaek", "kleiopat@gmail.com", "not filled yet", "monday-friday mornings");
        Student platonas = new Student("Platonas", "Karageorgis", "p3180068", "olimpiakara", "karaplato@gmail.com", "java,Computer science,Artificial Intelligence", "only afternoons");
        Student denis = new Student("Denis", "Diamantis", "p3150129", "denasxoloume", "denisdiam@gmail.com", "c++,android", "weekend all day,weekday varies from work ");
        Student nikos = new Student("Nikos", "Ioannou", "p3180200", "nikos318", "nikos@gmail.com", "not filled yet", "all mornings");
        Student kostas = new Student("Kostas", "Karageorgis", "p3180008", "kostas318", "kostas@gmail.com", "java,maths,Artificial Intelligence", "only afternoons and weekends");
        Student giannis = new Student("Giannis", "Chatzis", "p3150139", "giannis315", "giannis@gmail.com", "c++,java,python,sql", "monday,thursday mornings else all others days afteroon");
        Student maria = new Student("Maria", "Deli", "p3180066", "maria318", "maria@gmail.com", "not filled yet", "monday-friday mornings,weekend afternoons");
        Student dimitris = new Student("Dimitris", "Avgeris", "p3180069", "dimitris318", "dimitris@gmail.com", "java,Computer science,android development,javascript", "only mornings");
        Student admin = new Student("Giorgos", "Giapitzakis", "admin", "adminpower", "georgegp@gmail.com", "null", "null");

        Account nikos_account = new Account(nikos);
        Account kostas_account = new Account(kostas);
        Account giannis_account = new Account(giannis);
        Account maria_account = new Account(maria);
        Account dimitris_account = new Account(dimitris);
        Account admin_account = new Account(admin);
        Account kleio_account = new Account(kleio);
        Account platonas_account = new Account(platonas);
        Account denis_account = new Account(denis);

        ArrayList<Student> membersR4 = new ArrayList<>();
        membersR4.add(kleio);
        membersR4.add(platonas);
        Team team_r4 = new Team(r4, platonas, membersR4, "Programming mindset,good understanding of software engineering,must have passed java");
        kleio.addTeams(team_r4);
        platonas.addTeams(team_r4);
        ArrayList<Student> membersTikTok = new ArrayList<>();
        membersTikTok.add(denis);
        membersTikTok.add(platonas);
        Team team_tik_tok = new Team(tik_tok, denis, membersTikTok, "Must have completed Operating Systems,know sockets and multithreading in java");
        denis.addTeams(team_tik_tok);
        platonas.addTeams(team_tik_tok);


        nikos.evaluate(5, kleio, team_r4);
        maria.evaluate(4, kleio, team_r4);
        getTeamDAO().saveTeam(team_r4);
        getTeamDAO().saveTeam(team_tik_tok);

        getAccountDAO().saveAccount(kleio_account);
        getAccountDAO().saveAccount(platonas_account);
        getAccountDAO().saveAccount(denis_account);
        getAccountDAO().saveAccount(nikos_account);
        getAccountDAO().saveAccount(kostas_account);
        getAccountDAO().saveAccount(giannis_account);
        getAccountDAO().saveAccount(maria_account);
        getAccountDAO().saveAccount(dimitris_account);
        getAccountDAO().setAdminAccount(admin_account);

        getTeamDAO().saveTeam(team_r4);
        getTeamDAO().saveTeam(team_tik_tok);
        getStudentDAO().saveStudent(denis);
        getStudentDAO().saveStudent(kleio);
        getStudentDAO().saveStudent(platonas);
        getStudentDAO().saveStudent(nikos);
        getStudentDAO().saveStudent(kostas);
        getStudentDAO().saveStudent(giannis);
        getStudentDAO().saveStudent(maria);
        getStudentDAO().saveStudent(dimitris);


        Request request_denis = new Request(denis, team_r4);
        Request request_kleio = new Request(kleio, team_tik_tok);
        Request request_giannis = new Request(giannis, team_tik_tok);
        Request request_dimitris = new Request(denis, team_tik_tok);
        Request request_nikos = new Request(nikos, team_tik_tok);
        team_r4.addRequests(request_denis);
        team_tik_tok.addRequests(request_kleio);
        team_tik_tok.addRequests(request_giannis);
        team_tik_tok.addRequests(request_dimitris);
        team_tik_tok.addRequests(request_nikos);


        getRequestDAO().saveRequest(request_kleio);
        getRequestDAO().saveRequest(request_denis);
        getRequestDAO().saveRequest(request_giannis);
        getRequestDAO().saveRequest(request_dimitris);
        getRequestDAO().saveRequest(request_nikos);


    }
    public abstract AccountDAO getAccountDAO();

    public abstract TeamDAO getTeamDAO();

    public abstract RequestDAO getRequestDAO();

    public abstract StudentDAO getStudentDAO();

    public abstract CourseDAO getCourseDao();

    public abstract ProjectDAO getProjectDAO();
}
