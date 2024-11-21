package com.memorydao;

import com.dao.AccountDAO;
import com.dao.CourseDAO;
import com.dao.Initializer;
import com.dao.ProjectDAO;
import com.dao.RequestDAO;
import com.dao.StudentDAO;
import com.dao.TeamDAO;

public class InitializerMemory extends Initializer {

    @Override
    public AccountDAO getAccountDAO() {
        return new AccountMemory();
    }

    @Override
    public TeamDAO getTeamDAO() {
        return new TeamMemory();
    }

    @Override
    public RequestDAO getRequestDAO() {
        return new RequestMemory();
    }

    @Override
    public StudentDAO getStudentDAO() {
        return new StudentMemory();
    }

    @Override
    public CourseDAO getCourseDao() {
        return new CourseMemory();
    }

    @Override
    public ProjectDAO getProjectDAO() {
        return new ProjectMemory();
    }
}
