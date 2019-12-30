package com.example.resep.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class HasilCari implements Parcelable {

    @SerializedName("id")
    private String id;

    @SerializedName("users_id")
    private String users_id;

    @SerializedName("name")
    private String name;

    @SerializedName("nama_resep")
    private String nama_resep;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("bahan")
    private String bahan;

    @SerializedName("cara_masak")
    private String cara_masak;

    @SerializedName("publish")
    private String publish;

    @SerializedName("lokasi_id")
    private int lokasi_id;

    @SerializedName("nama_lokasi")
    private String nama_lokasi;

    @SerializedName("jenis_id")
    private int jenis_id;

    @SerializedName("nama_jenis")
    private String nama_jenis;

    @SerializedName("created_at")
    private String created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsers_id() {
        return users_id;
    }

    public void setUsers_id(String users_id) {
        this.users_id = users_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNama_resep() {
        return nama_resep;
    }

    public void setNama_resep(String nama_resep) {
        this.nama_resep = nama_resep;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getCara_masak() {
        return cara_masak;
    }

    public void setCara_masak(String cara_masak) {
        this.cara_masak = cara_masak;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public int getLokasi_id() {
        return lokasi_id;
    }

    public void setLokasi_id(int lokasi_id) {
        this.lokasi_id = lokasi_id;
    }

    public String getNama_lokasi() {
        return nama_lokasi;
    }

    public void setNama_lokasi(String nama_lokasi) {
        this.nama_lokasi = nama_lokasi;
    }

    public int getJenis_id() {
        return jenis_id;
    }

    public void setJenis_id(int jenis_id) {
        this.jenis_id = jenis_id;
    }

    public String getNama_jenis() {
        return nama_jenis;
    }

    public void setNama_jenis(String nama_jenis) {
        this.nama_jenis = nama_jenis;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public HasilCari(Parcel in){
        id = in.readString();
        users_id = in.readString();
        name = in.readString();
        nama_resep = in.readString();
        deskripsi = in.readString();
        gambar = in.readString();
        bahan = in.readString();
        cara_masak = in.readString();
        publish = in.readString();
        lokasi_id = in.readInt();
        nama_lokasi = in.readString();
        jenis_id = in.readInt();
        nama_jenis = in.readString();
        created_at = in.readString();
    }

    public static final Creator<HasilCari> CREATOR = new Creator<HasilCari>() {
        @Override
        public HasilCari createFromParcel(Parcel in) {
            return new HasilCari(in);
        }

        @Override
        public HasilCari[] newArray(int size) {
            return new HasilCari[size];
        }
    };

    public HasilCari(String id, String users_id, String name, String nama_resep, String deskripsi, String gambar, String bahan, String cara_masak,
                 String publish, int lokasi_id, String nama_lokasi, int jenis_id, String nama_jenis, String created_at){
        this.id=id;
        this.users_id=users_id;
        this.name = name;
        this.nama_resep=nama_resep;
        this.deskripsi=deskripsi;
        this.gambar=gambar;
        this.bahan=bahan;
        this.cara_masak=cara_masak;
        this.publish=publish;
        this.lokasi_id=lokasi_id;
        this.nama_lokasi=nama_lokasi;
        this.jenis_id=jenis_id;
        this.nama_jenis=nama_jenis;
        this.created_at=created_at;
    }

    @Override
    public String toString(){
        return
                "HasilCari{" +
                        ",id = '" + id + '\'' +
                        ",users_id = '" + users_id + '\'' +
                        ",name = '" + name + '\'' +
                        ",nama_resep = '" + nama_resep + '\'' +
                        ",deskripsi = '" + deskripsi + '\'' +
                        ",gambar = '" + gambar + '\'' +
                        ",bahan = '" + bahan + '\'' +
                        ",cara_masak = '" + cara_masak + '\'' +
                        ",publish = '" + publish + '\'' +
                        ",lokasi_id = '" + lokasi_id + '\'' +
                        ",nama_lokasi = '" + nama_lokasi + '\'' +
                        ",jenis_id = '" + jenis_id + '\'' +
                        ",nama_jenis = '" + nama_jenis + '\'' +
                        ",created_at = '" + created_at + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(id);
        dest.writeString(users_id);
        dest.writeString(name);
        dest.writeString(nama_resep);
        dest.writeString(deskripsi);
        dest.writeString(gambar);
        dest.writeString(bahan);
        dest.writeString(cara_masak);
        dest.writeString(publish);
        dest.writeInt(lokasi_id);
        dest.writeString(nama_lokasi);
        dest.writeInt(jenis_id);
        dest.writeString(nama_jenis);
        dest.writeString(created_at);
    }
}
