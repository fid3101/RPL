package com.example.elis;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class akun extends AppCompatActivity {
    private static final String KEY_NAMA = "Nama Lengkap";
    private static final String KEY_NOHP = "Nomor Handphone";
    private static final String KEY_EMAIL = "Email";

    private TextView akun_nama, akun_nohp, akun_email;
    private Button btnGantiPass;
    private Button btnLogout;
    private ImageView btnRename;
    private FirebaseAuth firAuth;
    private String UserID;
    private FirebaseFirestore firestore;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_akun);

        akun_nama = findViewById(R.id.akun_nama);
        akun_nohp = findViewById(R.id.akun_noHp);
        akun_email = findViewById(R.id.akun_email);
        btnGantiPass = findViewById(R.id.akun_btnganpas);
        btnLogout = findViewById(R.id.akun_btnLogout);
        btnRename = findViewById(R.id.akun_rename);
        progressBar = findViewById(R.id.akun_progressBar);

        BottomNavigationView navbar = findViewById(R.id.bottomnav);
        navbar.setSelectedItemId(R.id.akun_menu);
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

        firestore = FirebaseFirestore.getInstance();
        UserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DocumentReference doc = firestore.collection("User").document(UserID);
        doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    String nama = documentSnapshot.getString(KEY_NAMA);
                    String nohp = documentSnapshot.getString(KEY_NOHP);
                    String email = documentSnapshot.getString(KEY_EMAIL);

                    akun_nama.setText(""+nama);
                    akun_nohp.setText(""+nohp);
                    akun_email.setText(""+email);
                }
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(akun.this,login.class));
            }
        });
        btnRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(akun.this,reChangeProfil.class));
            }
        });
    }
}
