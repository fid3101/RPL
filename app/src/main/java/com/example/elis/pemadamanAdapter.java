package com.example.elis;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<pemadamanCardview> arrayList;

    public RecyclerViewAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_pemadaman,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        pemadamanCardview kll = arrayList.get(position);
        String id = kll.getId();
        String hari = kll.getHari();
        String tanggal = kll.getTanggal();
        String waktu = kll.getWaktu();
        String tempat = kll.getTempat();
        String timestamp = kll.getTimestamp();

        holder.hari.setText(hari);
        holder.tanggal.setText(tanggal);
        holder.waktu.setText(waktu);
        holder.tempat.setText(tempat);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView hari, tanggal, waktu, tempat;

        public ViewHolder (@NonNull View itemView){
            super(itemView);
            hari = itemView.findViewById(R.id.hari);
            tanggal = itemView.findViewById(R.id.tanggal);
            waktu = itemView.findViewById(R.id.jam);
            tempat = itemView.findViewById(R.id.lokasi);

        }
    }
}
