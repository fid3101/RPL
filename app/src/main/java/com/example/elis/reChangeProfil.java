package com.example.elis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

// TODO: memperbaiki supaya bisa kembali ke akun tanpa crash

public class reChangeProfil extends AppCompatActivity {
    private static final String KEY_NAMA = "Nama Lengkap";
    private static final String KEY_NOHP = "Nomor Handphone";
    private static final String KEY_EMAIL = "Email";

    private EditText proNama, proNohp;
    private Button proSimpan, proBack;
    private FirebaseFirestore firestore;
    private DatabaseReference reference;
    private String UserID;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_change_profil);

        proNama = (EditText)findViewById(R.id.profil_nama);
        proNohp = (EditText)findViewById(R.id.profil_nohp);
       // proEmail = (EditText)findViewById(R.id.profil_email);
        //proPassword = (EditText)findViewById(R.id.profil_password);

        proSimpan = (Button)findViewById(R.id.profil_btnSimpan);
        proBack = (Button)findViewById(R.id.profil_btnBack);
        progressBar = (ProgressBar)findViewById(R.id.profil_progress);


        //firebaseAuth = FirebaseAuth.getInstance();

        firestore =FirebaseFirestore.getInstance();

        UserID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        proSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nama = proNama.getText().toString();
                String Nohp = proNohp.getText().toString();
                //String Email = proEmail.getText().toString();
                //String pass = proPassword.getText().toString();

                progressBar.setVisibility(View.VISIBLE);
                // TODO: Apakah perlu ditambahkan ganti Email? menurutku tidak perlu
                /*FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(),pass);

                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("TAG", "User re-authenticated.");
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        user.updateEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Log.d("TAG", "User email address updated.");
                                }
                            }
                        });
                    }
                }); */

                DocumentReference doc = firestore.collection("User").document(UserID);
                Map<String, Object> update = new HashMap<>();
                startActivity(new Intent(getApplicationContext(),homeActivity.class));
                update.put(KEY_NAMA, Nama);
                update.put(KEY_NOHP, Nohp);
                doc.update(update);
                startActivity(new Intent(getApplicationContext(),homeActivity.class));
            }
        });
        proBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),homeActivity.class));
            }
        });


    }
}
