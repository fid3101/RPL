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

import java.util.HashMap;
import java.util.Map;

public class addPerangkat extends AppCompatActivity {

    private static final String KEY_JENIS = "Jenis Peragkat";
    private static final String KEY_RUANG = "Rangan";

    private RadioGroup perangkat;
    private RadioButton perangkatButton;
    private EditText Ruangan;
    private ImageButton Simpan;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_perangkat);
        perangkat= findViewById(R.id.radio);

        Ruangan= findViewById(R.id.editRuangan);
        Simpan= findViewById(R.id.simpan);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.login_progressBar);

        //TODO: hubungin ke FireBase, intent ke fragmen kelola belum bener
        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int jenis = perangkat.getCheckedRadioButtonId();
                perangkatButton = findViewById(jenis);
                String ruang = Ruangan.getText().toString();
                progressBar.setVisibility(View.VISIBLE);

                Map<String, Object> perangkat = new HashMap<>();
                perangkat.put("idjenis",jenis);
                perangkat.put("jenis",perangkatButton);
                perangkat.put("ruangan",ruang);

                startActivity(new Intent(getApplicationContext(),kelola.class));

            }});


        }
}

