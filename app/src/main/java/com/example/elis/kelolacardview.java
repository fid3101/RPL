package com.example.elis;

public class kelolacardview {
    private String id;
    private String jenis;
    private String ruangan;
    private String timestamp;



    public kelolacardview(String id, String jenis, String ruangan, String timestamp) {
        this.id = id;
        this.jenis = jenis;
        this.ruangan = ruangan;
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

    public String getJenis() {
        return jenis;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }
}
