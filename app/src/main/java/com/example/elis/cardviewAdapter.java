package com.example.elis;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private ArrayList<kelolacardview> arrayList;

    public Adapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_kelola,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        kelolacardview kll = (kelolacardview) arrayList.get(position);
        String id = kll.getId();
        String jenis = kll.getJenis();
        String ruangan = kll.getRuangan();

        holder.textPerangkat.setText(jenis);
        holder.textRuangan.setText(ruangan);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textPerangkat, textRuangan;

        public ViewHolder (@NonNull View itemView){
            super(itemView);
            textPerangkat = itemView.findViewById(R.id.perangkat);
            textRuangan = itemView.findViewById(R.id.ruangan);

        }
    }
}
