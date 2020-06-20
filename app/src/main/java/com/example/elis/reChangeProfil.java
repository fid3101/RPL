package com.example.elis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

// TODO: memperbaiki supaya bisa kembali ke nav_akun tanpa crash

public class reChangeProfil extends AppCompatActivity {
    private static final String KEY_NAMA = "Nama Lengkap";
    private static final String KEY_NOHP = "Nomor Handphone";
    private static final String KEY_EMAIL = "Email";

    private EditText proNama, proNohp, proEmail;
    private Button proSimpan, proBack;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private String UserID;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_change_profil);

        proNama = (EditText)findViewById(R.id.profil_nama);
        proNohp = (EditText)findViewById(R.id.profil_nohp);
        proEmail = (EditText)findViewById(R.id.profil_email);

        proSimpan = (Button)findViewById(R.id.profil_btnSimpan);
        proBack = (Button)findViewById(R.id.profil_btnBack);
        progressBar = (ProgressBar)findViewById(R.id.profil_progress);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        UserID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        proSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nama = proNama.getText().toString();
                String Nohp = proNohp.getText().toString();
                String Email = proEmail.getText().toString();

                progressBar.setVisibility(View.VISIBLE);
                DocumentReference doc = firestore.collection("User").document(UserID);
                Map<String, Object> update = new HashMap<>();
                update.put(KEY_NAMA, Nama);
                update.put(KEY_NOHP,Nohp);
                doc.set(update);
                startActivity(new Intent(getApplicationContext(),akun.class));
            }
        });
        proBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),akun.class));
            }
        });


    }
}
