package com.example.resep.model;

import com.google.gson.annotations.SerializedName;

public class Jenis {
    @SerializedName("id")
    private int id;

    @SerializedName("nama_jenis")
    private String nama_jenis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_jenis() {
        return nama_jenis;
    }

    public void setNama_jenis(String nama_jenis) {
        this.nama_jenis = nama_jenis;
    }

    @Override
    public String toString(){
        return
                "Jenis{"+
                        ",id = '"+ id + '\'' +
                        ",nama_jenis = '" + nama_jenis + '\'' +
                        "}";
    }
}
