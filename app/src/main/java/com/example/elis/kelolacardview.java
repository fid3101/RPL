package com.example.elis;

import android.widget.Switch;

public class kelolacardview {

    private String Jenis;
    private String Ruangan;
    private Switch Mswitch;

    public kelolacardview(String jenis, String ruangan, Switch mswitch){
        Jenis = jenis;
        Ruangan = ruangan;
        Mswitch = mswitch;
    }

    public String getJenis() {
        return Jenis;
    }

    public String getRuangan() {
        return Ruangan;
    }

    public Switch getMswitch() {
        return Mswitch;
    }


    public void setJenis(String jenis) {
        Jenis = jenis;
    }

    public void setRuangan(String ruangan) {
        Ruangan = ruangan;
    }

    public void setMswitch(Switch mswitch) {
        Mswitch = mswitch;
    }
}
