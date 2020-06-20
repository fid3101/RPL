package com.example.elis;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class alarm extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_alarm);

        BottomNavigationView navbar = findViewById(R.id.bottomnav);
        navbar.setSelectedItemId(R.id.alarm_menu);
        navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_menu:
                        startActivity(new Intent(getApplicationContext(), home.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.info_menu:
                        startActivity(new Intent(getApplicationContext(), info.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.alarm_menu:
                        startActivity(new Intent(getApplicationContext(), alarm.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.kelola_menu:
                        startActivity(new Intent(getApplicationContext(), kelola.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.akun_menu:
                        startActivity(new Intent(getApplicationContext(), akun.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

}
