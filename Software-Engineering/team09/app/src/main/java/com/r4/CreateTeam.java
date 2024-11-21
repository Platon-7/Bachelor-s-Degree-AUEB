package com.r4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CreateTeam extends AppCompatActivity implements CreateTeamView {

    private String user;
    private String course;
    private String requirements;
    Button createTeam_btn;
    EditText course_txt;
    EditText requirements_txt;
    CreateTeamPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        setTitle("Create Team");
        user=getIntent().getStringExtra(Menu.CURRENT_USER_AM);
        course_txt=findViewById(R.id.teamCourse);
        requirements_txt=findViewById(R.id.teamReq);
        createTeam_btn=findViewById(R.id.createTeam);
        CreateTeamViewModel viewModel=new ViewModelProvider(this).get(CreateTeamViewModel.class);
        presenter=viewModel.getPresenter();
        presenter.setView(this);
        createTeam_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course=course_txt.getText().toString();
                requirements=requirements_txt.getText().toString();
                presenter.createTeam(course,user,requirements);
            }
        });
    }

    @Override
    public void showErrorCourse(String output) {
        Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(String success) {
        Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void showErrorRequirements(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showErrorCheckTeams(String checkTeams) {
        Toast.makeText(getApplicationContext(), checkTeams, Toast.LENGTH_SHORT).show();
    }
}