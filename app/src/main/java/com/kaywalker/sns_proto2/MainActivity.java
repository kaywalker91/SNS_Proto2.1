package com.kaywalker.sns_proto2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.kaywalker.sns_proto2.fragment.Frag_Home;
import com.kaywalker.sns_proto2.fragment.Frag_Profile;
import com.kaywalker.sns_proto2.fragment.Frag_UserList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag_Home fragment_home;
    private Frag_Profile fragment_profile;
    private Frag_UserList fragment_userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        setFrag(0);
                        break;
                    case R.id.action_profile:
                        setFrag(1);
                        break;
                    case R.id.action_userlist:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });

        fragment_home = new Frag_Home();
        fragment_profile = new Frag_Profile();
        fragment_userlist = new Frag_UserList();

        setFrag(0);

    }

    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.main_frame, fragment_home);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, fragment_profile);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, fragment_userlist);
                ft.commit();
                break;
        }
    }
}