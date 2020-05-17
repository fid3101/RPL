package com.example.elis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

public class gantiPassword extends AppCompatActivity {
    private EditText reNamaold, renamaNew, conNamanew;
    private Button btnSimpan, btnBack;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_password);
        reNamaold = (EditText)findViewById(R.id.ganti_oldpass);
        renamaNew = (EditText)findViewById(R.id.ganti_newpass);
        conNamanew = (EditText)findViewById(R.id.ganti_conNewpass);

        btnBack = (Button)findViewById(R.id.ganti_btnBack);
        btnSimpan = (Button)findViewById(R.id.ganti_btnSimpan);
        progressBar = (ProgressBar)findViewById(R.id.ganti_progressBar);


        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passold = reNamaold.getText().toString();
                final String passnew = renamaNew.getText().toString();
                String conPassnew = conNamanew.getText().toString();

                if(TextUtils.isEmpty(passold)){
                    reNamaold.setError("Mohon masukkan sandi lama anda");
                    return;
                }
                if(TextUtils.isEmpty(passnew)){
                    renamaNew.setError("Mohon masukkan sandi baru anda");
                    return;
                }
                if(TextUtils.isEmpty(conPassnew)){
                    conNamanew.setError("Kolom konfirmasi tidak boleh kososng");
                    return;
                }
                if(passnew.length()<6){
                    renamaNew.setError("Password harus lebih dari 6 karakter");
                    return;
                }
                if(!passnew.equals(conPassnew)){
                    renamaNew.setError("Mohon masukkan password yang sesuai");
                    conNamanew.setError("Mohon masukkan password yang sesuai");
                    return;
                }

                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(),passold);

                progressBar.setVisibility(View.VISIBLE);
                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            user.updatePassword(passnew).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"Ubah password berhasil",Toast.LENGTH_SHORT);
                                        startActivity(new Intent(getApplicationContext(),homeActivity.class));
                                    }else{
                                        Toast.makeText(getApplicationContext(),"Gagal mengubah password",Toast.LENGTH_SHORT);
                                        return;
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(getApplicationContext(),"Terjadi Kesalahan",Toast.LENGTH_SHORT);
                        }
                    }
                });

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: tambahkan kode kembali ke halaman akun
                startActivity(new Intent(getApplicationContext(),homeActivity.class));
            }
        });
    }
}
