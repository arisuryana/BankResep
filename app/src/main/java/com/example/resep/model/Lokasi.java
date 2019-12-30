package com.example.resep.model;

import com.google.gson.annotations.SerializedName;

public class Lokasi {
    @SerializedName("id")
    private int id;

    @SerializedName("nama_lokasi")
    private String nama_lokasi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_lokasi() {
        return nama_lokasi;
    }

    public void setNama_lokasi(String nama_lokasi) {
        this.nama_lokasi = nama_lokasi;
    }

    @Override
    public String toString(){
        return
                "Lokasi{"+
                        ",id = '"+ id + '\'' +
                        ",nama_lokasi = '" + nama_lokasi + '\'' +
                        "}";
    }
}
