package com.r4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.memorydao.InitializerMemory;

public class LoginScreen extends AppCompatActivity implements  LoginView{

    LoginPresenter presenter;
    static boolean flag=true;
    InitializerMemory initializer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        setTitle("Project Partners");
        if(flag) {
            initializer = new InitializerMemory();
            initializer.prepareData();
            flag=false;
        }
        EditText AMText = findViewById(R.id.AM);
        EditText PasswordText = findViewById(R.id.Password);
        Button LoginButton = findViewById(R.id.Login);
        Button SignUpButton = findViewById(R.id.SignUp);
        LoginScreenViewModel viewModel= new ViewModelProvider(this).get(LoginScreenViewModel.class);
        presenter=viewModel.getPresenter();
        presenter.setView(this);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AM = AMText.getText().toString();
                String password = PasswordText.getText().toString();
                presenter.login(AM,password);
            }
        });
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signUp();
            }
        });
    }
    public void signUp(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
    public void showMenu(String AM){
        Intent intent = new Intent(this,Menu.class);
        intent.putExtra(Menu.CURRENT_USER_AM,AM);
        startActivity(intent);
    }

    @Override
    public void ShowError(String error) {
        Toast.makeText(this,error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRegisterScreen() {
        Intent intent = new Intent(this, RegisterProject.class);
        startActivity(intent);
    }

}