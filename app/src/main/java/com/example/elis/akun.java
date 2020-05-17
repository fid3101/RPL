package com.example.elis;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link akun#newInstance} factory method to
 * create an instance of this fragment.
 */
public class akun extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
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


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public akun() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment akun.
     */
    // TODO: Rename and change types and number of parameters
    public static akun newInstance(String param1, String param2) {
        akun fragment = new akun();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_akun, container, false);
        akun_nama = v.findViewById(R.id.akun_nama);
        akun_nohp = v.findViewById(R.id.akun_noHp);
        akun_email = v.findViewById(R.id.akun_email);
        btnGantiPass = v.findViewById(R.id.akun_btnganpas);
        btnLogout = v.findViewById(R.id.akun_btnLogout);
        btnRename = v.findViewById(R.id.akun_rename);
        progressBar = v.findViewById(R.id.akun_progressBar);


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
                firAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(),login.class));
            }
        });
        btnRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),reChangeProfil.class));
            }
        });

        btnGantiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),gantiPassword.class));
            }
        });

        return v;
    }
}
