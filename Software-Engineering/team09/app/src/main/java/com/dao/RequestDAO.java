package com.dao;

import com.r4.Request;
import com.r4.Student;

import java.util.List;

public interface RequestDAO {

    public void saveRequest(Request request);

    public List<Request> getRequests(String user);
    public void deleteRequest(Request request);
}
