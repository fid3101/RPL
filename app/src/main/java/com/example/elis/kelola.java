package com.example.elis;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.common.internal.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class kelola extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    public static String KEY_ACTIVITY = "msg_activity";
    ImageButton add;
    DataHelper dataHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_kelola);

        BottomNavigationView navbar = findViewById(R.id.bottomnav);
        navbar.setSelectedItemId(R.id.kelola_menu);
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

        recyclerView = findViewById(R.id.recycler_kelola);
        dataHelper = new DataHelper(this);
        adapter = new Adapter(kelola.this, dataHelper.getAllData( "id DESC"));
        recyclerView.setAdapter(adapter);

        add = findViewById(R.id.addPerangkat);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), addPerangkat.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new Adapter(kelola.this, dataHelper.getAllData( "id DESC"));
        recyclerView.setAdapter(adapter);
    }
}
