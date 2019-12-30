package com.example.resep.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;


public class Profile implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public Profile(Parcel in) {
        updatedAt = in.readString();
        name = in.readString();
        createdAt = in.readString();
        id = in.readInt();
        email = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public Profile(int id, String name, String email) {
        this.id=id;
        this.name=name;
        this.email=email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString(){
        return
                "Profile{" +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",name = '" + name + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",id = '" + id + '\'' +
                        ",email = '" + email + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(updatedAt);
        dest.writeString(name);
        dest.writeString(createdAt);
        dest.writeInt(id);
        dest.writeString(email);
    }

    public class Entry implements BaseColumns {
        public static final String TABLE_NAME="profile";
        public static final String COLUMN_ID="id";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_EMAIL="email";
    }
}
