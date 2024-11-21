package com.r4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.dao.RequestDAO;
import com.memorydao.RequestMemory;
import java.util.List;

public class Messages extends AppCompatActivity implements RequestFragment.OnRequestListener {
    private String user;
    RequestDAO requestDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = intent.getStringExtra(Menu.CURRENT_USER_AM);
        requestDAO=new RequestMemory();
        setContentView(R.layout.activity_messages);
        setTitle("Messages");
    }

    @Override
    public void AcceptSelection(Request request) {
        if (request.getApplicationTeam().acceptRequest(request)){
            Toast.makeText(getApplicationContext(), "Member added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "You have reached the maximum size of members", Toast.LENGTH_SHORT).show();
        }
        requestDAO.deleteRequest(request);
    }

    @Override
    public void DeclineSelection(Request request) {
        request.getApplicationTeam().declineRequest(request);
        requestDAO.deleteRequest(request);
    }

    @Override
    public List<Request> getRequests() {
        return requestDAO.getRequests(user);
    }
}