package com.example.resep.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;

public class Favorite implements Parcelable {

    @SerializedName("id")
    private String id;

    @SerializedName("users_id")
    private String users_id;

    @SerializedName("resep_id")
    private String resep_id;

    @SerializedName("nama_pemilik")
    private String nama_pemilik;

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

    @SerializedName("nama_lokasi")
    private String nama_lokasi;

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

    public String getResep_id() {
        return resep_id;
    }

    public void setResep_id(String resep_id) {
        this.resep_id = resep_id;
    }

    public String getNama_pemilik() {
        return nama_pemilik;
    }

    public void setNama_pemilik(String nama_pemilik) {
        this.nama_pemilik = nama_pemilik;
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

    public String getNama_lokasi() {
        return nama_lokasi;
    }

    public void setNama_lokasi(String nama_lokasi) {
        this.nama_lokasi = nama_lokasi;
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

    public Favorite(Parcel in){
        id = in.readString();
        users_id = in.readString();
        resep_id = in.readString();
        nama_pemilik = in.readString();
        nama_resep = in.readString();
        deskripsi = in.readString();
        gambar = in.readString();
        bahan = in.readString();
        cara_masak = in.readString();
        nama_lokasi = in.readString();
        nama_jenis = in.readString();
        created_at = in.readString();
    }

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel in) {
            return new Favorite(in);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };

    public Favorite(String id, String users_id, String resep_id, String nama_pemilik, String nama_resep, String deskripsi, String gambar, String bahan, String cara_masak,
                 String nama_lokasi, String nama_jenis, String created_at){
        this.id=id;
        this.users_id=users_id;
        this.resep_id=resep_id;
        this.nama_pemilik = nama_pemilik;
        this.nama_resep=nama_resep;
        this.deskripsi=deskripsi;
        this.gambar=gambar;
        this.bahan=bahan;
        this.cara_masak=cara_masak;
        this.nama_lokasi=nama_lokasi;
        this.nama_jenis=nama_jenis;
        this.created_at=created_at;
    }

    @Override
    public String toString(){
        return
                "Favorite{" +
                        ",id = '" + id + '\'' +
                        ",users_id = '" + users_id + '\'' +
                        ",resep_id = '" + resep_id + '\'' +
                        ",name = '" + nama_pemilik + '\'' +
                        ",nama_resep = '" + nama_resep + '\'' +
                        ",deskripsi = '" + deskripsi + '\'' +
                        ",gambar = '" + gambar + '\'' +
                        ",bahan = '" + bahan + '\'' +
                        ",cara_masak = '" + cara_masak + '\'' +
                        ",nama_lokasi = '" + nama_lokasi + '\'' +
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
        dest.writeString(resep_id);
        dest.writeString(nama_pemilik);
        dest.writeString(nama_resep);
        dest.writeString(deskripsi);
        dest.writeString(gambar);
        dest.writeString(bahan);
        dest.writeString(cara_masak);
        dest.writeString(nama_lokasi);
        dest.writeString(nama_jenis);
        dest.writeString(created_at);
    }

    public class Entry implements BaseColumns{
        public static final String TABLE_NAME="favorite";
        public static final String COLUMN_ID="id";
        public static final String COLUMN_USERS_ID="users_id";
        public static final String COLUMN_RESEP_ID="resep_id";
        public static final String COLUMN_NAMA_PEMILIK="nama_pemilik";
        public static final String COLUMN_NAMA_RESEP="nama_resep";
        public static final String COLUMN_DESKRIPSI="deskripsi";
        public static final String COLUMN_GAMBAR="gambar";
        public static final String COLUMN_BAHAN="bahan";
        public static final String COLUMN_CARA_MASAK="cara_masak";
        public static final String COLUMN_NAMA_LOKASI="nama_lokasi";
        public static final String COLUMN_NAMA_JENIS="nama_jenis";
        public static final String COLUMN_CREATED_AT="created_at";
    }
}
