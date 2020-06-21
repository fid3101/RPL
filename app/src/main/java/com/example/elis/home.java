package com.example.elis;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class home extends AppCompatActivity {

    private ImageButton kelolaPerangkat;
    private TextView jmlh;
    private int jumlah_per;
    DataHelper dataHelper;
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_home);

        kelolaPerangkat = findViewById(R.id.kelolaButton);
        jmlh = findViewById(R.id.nominal);

        BottomNavigationView navbar = findViewById(R.id.bottomnav);
        navbar.setSelectedItemId(R.id.home_menu);
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

        dataHelper = new DataHelper(this);
        jumlah_per = dataHelper.jumlahPerangkat();
        String j = Integer.toString(jumlah_per);
        jmlh.setText(j);

        recyclerView = findViewById(R.id.pemadaman_recyclerview);
        dataHelper = new DataHelper(this);
        adapter = new RecyclerViewAdapter(home.this, dataHelper.getAllDataPemadaman( "id DESC"));
        recyclerView.setAdapter(adapter);

        kelolaPerangkat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), kelola.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataHelper = new DataHelper(this);
        jumlah_per = dataHelper.jumlahPerangkat();
        String j = Integer.toString(jumlah_per);
        jmlh.setText(j);

    }

}
