package com.example.elis;

public class pemadamanCardview {
    private  String id;
    private String hari,tempat;
    private String tanggal;
    private String waktu;
    private  String timestamp;

    public pemadamanCardview(String id, String hari, String tempat, String tanggal, String waktu, String timestamp) {
        this.id = id;
        this.hari = hari;
        this.tempat = tempat;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}

