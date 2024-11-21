package com.r4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dao.AccountDAO;
import com.dao.StudentDAO;
import com.memorydao.AccountMemory;
import com.memorydao.StudentMemory;

public class EditInfo extends AppCompatActivity implements EditInfoView{
    Button change_password;
    Button update_skills;
    Button update_time;
    EditText oldPass_Txt;
    EditText newPass_Txt;
    EditText repeatPass_Txt;
    EditText editSkills_Txt;
    EditText editTime_Txt;
    EditInfoPresenter presenter;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        setTitle("Edit Info");
        Intent intent=getIntent();
        user=intent.getStringExtra(Menu.CURRENT_USER_AM);
        oldPass_Txt=findViewById(R.id.old_password);
        newPass_Txt=findViewById(R.id.new_password);
        repeatPass_Txt=findViewById(R.id.repeat_password);
        editSkills_Txt=findViewById(R.id.edit_skills);
        editTime_Txt=findViewById(R.id.edit_time);
        change_password = findViewById(R.id.change_password);
        update_skills=findViewById(R.id.update_skills);
        update_time=findViewById(R.id.update_time);
        EditInfoViewModel viewModel=new ViewModelProvider(this).get(EditInfoViewModel.class);
        presenter=viewModel.getPresenter();
        presenter.setView(this);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpassword=oldPass_Txt.getText().toString();
                String newpassword=newPass_Txt.getText().toString();
                String repeatpassword=repeatPass_Txt.getText().toString();
                presenter.changePassword(user,oldpassword,newpassword,repeatpassword);
            }
        });
        update_skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String skills=editSkills_Txt.getText().toString();
                presenter.updateSkills(user,skills);
            }
        });
        update_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time=editTime_Txt.getText().toString();
                presenter.updateTime(user,time);
            }
        });

    }

    @Override
    public void showErrorPassword(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorSize(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorPasswordEquality(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorNullSkills(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorNullTime(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessfulChange(String success) {
        Toast.makeText(getApplicationContext(), success ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessfulUpdateSkills(String success) {
        Toast.makeText(getApplicationContext(), success ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessfulUpdateTime(String success) {
        Toast.makeText(getApplicationContext(), success ,Toast.LENGTH_SHORT).show();
    }
}