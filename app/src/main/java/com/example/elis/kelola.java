package com.example.elis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kelola, container, false);
    }
}
