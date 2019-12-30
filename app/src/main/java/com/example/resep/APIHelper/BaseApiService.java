package com.example.resep.APIHelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface BaseApiService {
    //Memanggil API Login
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    //Memanggil API Register
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("name") String nama,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("c_password") String cpassword);

    //Fungsi Detail User
    @FormUrlEncoded
    @POST("detail")
    Call<ResponseBody> detailUser(@Field("id") Integer id);


    @FormUrlEncoded
    @PUT("updateProfile/")
    Call<ResponseBody> updateProfile(@Field("id") int id, @Field("email") String email, @Field("name") String name);

    @FormUrlEncoded
    @PUT("updatePassword/")
    Call<ResponseBody> updatePassword(@Field("id") int id, @Field("pass_lama") String pass_lama, @Field("pass_baru") String pass_baru);

}
