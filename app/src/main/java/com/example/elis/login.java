package com.example.elis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private TextInputLayout Email;
    private TextInputLayout Password;
    private ImageButton Login;
    private TextView Daftar;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email= findViewById(R.id.emailField);
        Password= findViewById(R.id.passwordField);
        Login= findViewById(R.id.btnLogin);
        Daftar= findViewById(R.id.daftar);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar)findViewById(R.id.login_progressBar);

        Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Registration.class));
            }
        });

       Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validate(Email.getEditText().getText().toString(),Password.getEditText().getText().toString());
                String email = Email.getEditText().getText().toString();
                String pass = Password.getEditText().getText().toString();

                if(TextUtils.isEmpty(email)){
                    Email.setError("E-mail Required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Password.setError("Password required");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            final AlertDialog.Builder builder = new AlertDialog.Builder(
                                    login.this,R.style.AlertDialogStyle
                            );
                            builder.setIcon(R.drawable.ic_check);
                            builder.setTitle("Login Sukses!");
                            builder.setMessage("Selamat datang di Elis");
                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    Intent intent = new Intent(login.this,homeActivity.class);
                                    startActivity(intent);
                                }
                            });

                            final AlertDialog alert=builder.create();
                            alert.show();

                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "Email atau Password Salah",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

private void validate(String userName, String userPassword){

        if((userName.equals("admin")) && (userPassword.equals("1234"))){

            final AlertDialog.Builder builder = new AlertDialog.Builder(
                    login.this,R.style.AlertDialogStyle
            );
            builder.setIcon(R.drawable.ic_check);
            builder.setTitle("Login Sukses!");
            builder.setMessage("Selamat datang di Elis");
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(login.this,homeActivity.class);
                    startActivity(intent);
                }
            });

            final AlertDialog alert=builder.create();
            alert.show();

        }else{
            Toast.makeText(getApplicationContext(),
                    "Email atau Password Salah",Toast.LENGTH_SHORT).show();
        }
    }
}
