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

public class SignUp extends AppCompatActivity implements SignUpView {

    SignUpPresenter presenter;
    String input_AM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Create new Account");
        EditText name = findViewById(R.id.Student_Name);
        EditText surname = findViewById(R.id.Student_Surname);
        EditText password1 = findViewById(R.id.Student_Password1);
        EditText password2 = findViewById(R.id.Student_Password2);
        EditText email = findViewById(R.id.Student_Email);
        EditText AM = findViewById(R.id.Student_AM);
        EditText skills = findViewById(R.id.Student_Skills);
        EditText timeline = findViewById(R.id.Student_Timeline);
        Button continue_btn = findViewById(R.id.button);
        SignUpViewModel viewModel=new ViewModelProvider(this).get(SignUpViewModel.class);
        presenter=viewModel.getPresenter();
        presenter.setView(this);
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 input_AM = AM.getText().toString();
                String input_password1 = password1.getText().toString();
                String input_password2 = password2.getText().toString();
                String input_email = email.getText().toString();
                String input_name = name.getText().toString();
                String input_surname= surname.getText().toString();
                String input_skills=skills.getText().toString();
                String input_timeline=timeline.getText().toString();
                presenter.signUp( input_AM, input_password1, input_password2, input_email, input_name, input_surname,input_skills,input_timeline );
            }
        });
    }
    public void showMenu(){
        Intent intent = new Intent(this,Menu.class);
        intent.putExtra(Menu.CURRENT_USER_AM,input_AM);
        startActivity(intent);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this,error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(String account_successfully_created) {
        Toast.makeText(this,account_successfully_created, Toast.LENGTH_SHORT).show();
    }

}