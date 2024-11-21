package com.r4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dao.CourseDAO;
import com.dao.Initializer;
import com.memorydao.CourseMemory;
import com.memorydao.InitializerMemory;

public class Menu extends AppCompatActivity implements MenuView{
    protected static final String CURRENT_USER_AM = "current user AM";
    public static final String COURSE_SEARCH = "course_search";
    Button search_team_btn;
    Button messages_btn;
    Button create_team_btn;
    Button my_teams;
    Button editInfo_btn;
    EditText course_Txt;
    MenuPresenter presenter;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent =getIntent();
        user=intent.getStringExtra(CURRENT_USER_AM);
        setContentView(R.layout.activity_menu);
        setTitle("Menu");
        search_team_btn = findViewById(R.id.Search_Button);
        messages_btn = findViewById(R.id.Message_Button);
        create_team_btn = findViewById(R.id.Create_Team_Button);
        my_teams = findViewById(R.id.My_Teams_Button);
        course_Txt = findViewById(R.id.Enter_Course);
        editInfo_btn =findViewById(R.id.editInfo);
        MenuViewModel viewModel=new ViewModelProvider(this).get(MenuViewModel.class);
        presenter=viewModel.getPresenter();
        presenter.setView(this);
        search_team_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  course=course_Txt.getText().toString();
                presenter.showResults(course,user);

            }
        });
        messages_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showMessages(user);
            }
        });
        create_team_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createTeam();
            }
        });
        my_teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.myTeams();
            }
        });
        editInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.editInfo();
            }
        });
    }
    public void showMenu(){
        Intent intent = new Intent(this,CreateTeam.class);
        startActivity(intent);
    }

    @Override
    public void showError(String not_null) {
        Toast.makeText(getApplicationContext(), not_null, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResults(String course,String user) {
        Intent intent = new Intent(this,SearchResults.class);
        intent.putExtra(CURRENT_USER_AM,user);
        intent.putExtra(COURSE_SEARCH,course);
        startActivity(intent);
    }

    @Override
    public void showMessages(String user){
        Intent intent = new Intent(this,Messages.class);
        intent.putExtra(CURRENT_USER_AM,user);
        startActivity(intent);
    }

    @Override
    public void createTeam() {
        Intent intent = new Intent(this,CreateTeam.class);
        intent.putExtra(CURRENT_USER_AM,user);
        startActivity(intent);
    }


    @Override
    public void showErrorCourse(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void myTeams() {
        Intent intent = new Intent(this,MyTeams.class);
        intent.putExtra(CURRENT_USER_AM,user);
        startActivity(intent);
    }

    @Override
    public void editInfo() {
        Intent intent = new Intent(this,EditInfo.class);
        intent.putExtra(CURRENT_USER_AM,user);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,LoginScreen.class);
        startActivity(intent);
        super.onBackPressed();
    }
}