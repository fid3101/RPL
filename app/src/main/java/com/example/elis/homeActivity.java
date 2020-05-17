package com.example.elis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navbar = findViewById(R.id.bottomnav);
        navbar.setOnNavigationItemSelectedListener(navlistener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.home_menu:
                            selectedFragment = new home();
                            break;
                        case R.id.info_menu:
                            selectedFragment = new info();
                            break;
                        //case R.id.alarm_menu:
                        //    selectedFragment = new alarm();
                        //    break;
                        case R.id.kelola_menu:
                            selectedFragment = new kelola();
                            break;
                        case R.id.akun_menu:
                            selectedFragment = new akun();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment,selectedFragment).commit();

                    return true;
                }
            };
}
