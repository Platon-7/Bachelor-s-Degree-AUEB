package com.memorydao;

import com.dao.RequestDAO;
import com.r4.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestMemory implements RequestDAO {
    static List<Request> requests=new ArrayList<>();
    @Override
    public void saveRequest(Request request) {
        if(!requests.contains(request)){
            requests.add(request);
        }
    }



    @Override
    public List<Request> getRequests(String user) {
        List<Request> list = new ArrayList<>();

        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).getApplicationTeam().getFounder().getAM().equals(user)) {
                list.add(requests.get(i));
            }
        }
        return list;
    }

    @Override
    public void deleteRequest(Request request) {
        if(requests.contains(request)){
            requests.remove(request);
        }
    }
}