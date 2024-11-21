package com.r4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterProject extends AppCompatActivity implements RegisterProjectView {
    EditText course_txt;
    EditText limit_txt;
    EditText deadline_txt;
    Button sumbit_btn;
    RegisterProjectPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_project);
        setTitle("Register Project");
        course_txt=findViewById(R.id.projectname);
        limit_txt=findViewById(R.id.limit);
        deadline_txt=findViewById(R.id.deadlineProject);
        sumbit_btn=findViewById(R.id.submit);
        RegisterProjectViewModel viewModel=new ViewModelProvider(this).get(RegisterProjectViewModel.class);
        presenter=viewModel.getPresenter();
        presenter.setView(this);
        sumbit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course=course_txt.getText().toString();
                String limit=limit_txt.getText().toString();
                String deadline=deadline_txt.getText().toString();
                presenter.createProject(course,limit,deadline);
            }
        });

    }


    @Override
    public void showSuccess(String project_successfully) {
        Toast.makeText(getApplicationContext(), project_successfully, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String porject_already_exists) {
        Toast.makeText(getApplicationContext(), porject_already_exists, Toast.LENGTH_SHORT).show();
    }
}