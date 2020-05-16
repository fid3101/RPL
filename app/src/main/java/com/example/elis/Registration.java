package com.example.elis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    private EditText regNamaLengkap, regEmail, regAlamat, regNohanphone, regPassword, regConfirmPassword;
    private ImageButton regBtn;
    private TextView regTologin;
    private ProgressBar      progress;
    private FirebaseAuth    firebaseAuth;
    private FirebaseFirestore firestore;
    private String UserID;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        regNamaLengkap = (EditText)findViewById(R.id.regNamalengkap);
        regEmail = (EditText)findViewById(R.id.regEmail);
        regNohanphone = (EditText)findViewById(R.id.regNohanphone);
        regAlamat = (EditText)findViewById(R.id.regAlamat) ;
        regPassword = (EditText)findViewById(R.id.regPassword);
        regConfirmPassword = (EditText)findViewById(R.id.regConfirmPassword);
        regBtn = (ImageButton)findViewById(R.id.regBtnsignup);
        regTologin = (TextView)findViewById(R.id.regBtntoLogin);
        progress = (ProgressBar)findViewById(R.id.regProgressBar);

        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();


        /*if(firebaseAuth.getCurrentUser() != null ){
            startActivity(new Intent(getApplicationContext(), login.class));
            finish();
        } */


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Email = regEmail.getText().toString().trim();
                String Pass = regPassword.getText().toString().trim();
                String conPass = regConfirmPassword.getText().toString().trim();
                final String Nama = regNamaLengkap.getText().toString();
                final String NoHp = regNohanphone.getText().toString();
                final String Alamat = regAlamat.getText().toString();


                if(TextUtils.isEmpty(Email)){
                    regEmail.setError("E-mail Required");
                    return;
                }
                if(TextUtils.isEmpty(Pass)){
                    regPassword.setError("Password required");
                    return;
                }
                if(Pass.length() < 6){
                    regPassword.setError("Panjang karakter harus lebih dari 8");
                    return;
                }
                if(!Pass.equals(conPass)){
                    regConfirmPassword.setError("Mohon masukkan password yang sesuai");
                    return;
                }

                if(TextUtils.isEmpty(Nama)){
                    regNamaLengkap.setError("Masukkan nama lengkap");
                    return;
                }
                if(TextUtils.isEmpty(NoHp)){
                    regNohanphone.setError("Masukkan nomor handphone");
                    return;
                }
                if(TextUtils.isEmpty(Alamat)){
                    regPassword.setError("Masukkkan Alamat");
                    return;
                }

                progress.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Registration.this,"Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                            UserID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firestore.collection("User").document(UserID);
                            Map<String,Object> User = new HashMap<>();
                            User.put("Nama Lengkap", Nama);
                            User.put("Email", Email);
                            User.put("Nomor Handphone", NoHp);
                            User.put("Alamat", Alamat);
                            documentReference.set(User).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("Tag", " Profil pengguna berhasil ditambahkan"+UserID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), login.class));
                        }
                        else{
                            Toast.makeText(Registration.this,"Terjadi Kesalahan", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
        regTologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, login.class));
            }
        });
    }

}
