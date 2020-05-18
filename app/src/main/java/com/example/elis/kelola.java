package com.example.elis;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;

import java.util.List;

import io.grpc.NameResolver;

public class kelola extends Fragment {

    // TODO: Rename and change types of parameters
    private ImageButton tambah;
    private String jenis;
    private String ruangan;
    private Switch mswitch;
/*
    public kelola(Context mcontext, List<kelolacardview> mdata) {
        // Required empty public constructor
        this.mcontext=mcontext;
        this.mdata=mdata;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            jenis = getArguments().getString(ARG_JENIS);
            ruangan = getArguments().getString(ARG_RUANGAN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_kelola, container, false);
    }*/
}
