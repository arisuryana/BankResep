package com.example.resep.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("token")
    private String token;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("fcm_token")
    private String fcm_token;

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
                "User{"+
                        ",id = '"+ id + '\'' +
                        ",name = '" + name + '\'' +
                        ",email = '" + email + '\'' +
                        ",token = '" + token + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",fcm_token = '" + fcm_token + '\'' +
                        "}";
    }
}
