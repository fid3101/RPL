package com.example.elis;

import android.content.Intent;
import android.icu.util.ValueIterator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import io.grpc.NameResolver;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link kelola#newInstance} factory method to
 * create an instance of this fragment.
 */
public class kelola extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_JENIS = "jenis";
    private static final String ARG_RUANGAN = "ruangan";
    private static final String ARG_MSWITCH = "mswith";

    // TODO: Rename and change types of parameters
    private String jenis;
    private String ruangan;
    private Switch mswitch;
    private Button btnAdd;

    private RecyclerView firestorelist;
    private FirebaseFirestore firestore;
    private FirestoreRecyclerAdapter adapter;

    public kelola() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static kelola newInstance(String jenis, String ruangan, Switch mswitch) {
        kelola fragment = new kelola();
        Bundle args = new Bundle();
        args.putString(ARG_JENIS, jenis);
        args.putString(ARG_RUANGAN, ruangan);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            jenis = getArguments().getString(ARG_JENIS);
            ruangan = getArguments().getString(ARG_RUANGAN);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kelola, container, false);
        btnAdd = view.findViewById(R.id.kelola_btnAdd);
        firestorelist = view.findViewById(R.id.recyclerview_kelola);
        firestore = FirebaseFirestore.getInstance();

        Query query = firestore.collection("Lampu");

        FirestoreRecyclerOptions<lamp_class> options =new FirestoreRecyclerOptions.Builder<lamp_class>()
                .setQuery(query, lamp_class.class).build();

        adapter = new FirestoreRecyclerAdapter<lamp_class, lampViewHolder>(options) {
            @NonNull
            @Override
            public lampViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_kelola, parent, false);
                return new lampViewHolder(view1);
            }

            @Override
            protected void onBindViewHolder(@NonNull lampViewHolder holder, int position, @NonNull lamp_class model) {
                    holder.lampname.setText(model.getLampu());
                    holder.kondisi.setChecked(model.isKondisi());
            }
        };

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), addLampu.class));
            }
        });

        //view holder
        firestorelist.setHasFixedSize(true);
        firestorelist.setLayoutManager(new LinearLayoutManager(getActivity()));
        firestorelist.setAdapter(adapter);


        return view;

    }

    private class lampViewHolder extends  RecyclerView.ViewHolder{
        private TextView lampname;
        private Switch kondisi;

        public lampViewHolder(@NonNull View itemView) {
            super(itemView);
            lampname = itemView.findViewById(R.id.ruangan);
            kondisi = itemView.findViewById(R.id.add_switch);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
