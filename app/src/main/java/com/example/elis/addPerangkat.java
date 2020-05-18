package com.example.elis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class addPerangkat extends AppCompatActivity {
    private RadioGroup jenisperangkat;
    private EditText Ruangan;
    private ImageButton Simpan;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        jenisperangkat= findViewById(R.id.radio);
        Ruangan= findViewById(R.id.editRuangan);
        Simpan= findViewById(R.id.simpan);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.login_progressBar);


        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup jenis = jenisperangkat.getCheckedRadioButtonId();
                String ruang = Ruangan.getText().toString();

                progressBar.setVisibility(View.VISIBLE);

                });

            }
        }
}

