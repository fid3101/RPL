package com.example.elis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ObjectInputValidation;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

public class addPerangkat extends AppCompatActivity {

    private static final String KEY_JENIS = "Jenis Peragkat";
    private static final String KEY_RUANG = "Rangan";

    private RadioGroup jenis;
    private RadioButton lampu, listrik;
    private EditText Ruangan;
    private ImageButton Simpan;
    private ProgressBar progressBar;
    private DataHelper dbHelper;
    private String j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_perangkat);
        jenis= findViewById(R.id.radio);
        lampu= findViewById(R.id.lampu);
        listrik= findViewById(R.id.listrik);

        Ruangan= findViewById(R.id.editRuangan);
        Simpan= findViewById(R.id.simpan);
        progressBar = findViewById(R.id.login_progressBar);

        dbHelper = new DataHelper(this);

        jenis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.lampu){
                    j="Lampu";
                }
                else if(i==R.id.listrik){
                    j="Stop Kontak";
                }
            }
        });

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(j==null) j="Lampu";
                getData();
                startActivity(new Intent(getApplicationContext(),kelola.class));
            }

            private void getData() {
                String ruang = ""+Ruangan.getText().toString().trim();
                String timestamp = ""+System.currentTimeMillis();
                long id = dbHelper.insertInfo(
                        ""+j,
                        ""+ruang,
                        ""+timestamp
                );

                Toast.makeText(addPerangkat.this,"Perangkat sudah ditambahkan",Toast.LENGTH_SHORT).show();
            }
        });

        }
}

