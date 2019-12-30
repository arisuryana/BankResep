package com.example.resep.APIHelper;

import com.example.resep.model.Favorite;
import com.example.resep.model.HasilCari;
import com.example.resep.model.Jenis;
import com.example.resep.model.Lokasi;
import com.example.resep.model.Profile;
import com.example.resep.model.Resep;
import com.example.resep.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("login")
    Call<User> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<User> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("c_password") String cpassword,
            @Field("fcm_token") String fcmToken
    );

    @FormUrlEncoded
    @POST("update-fcm-token")
    Call<ResponseBody> updateFcmToken(
            @Field("id")String id,
            @Field("fcm_token")String fcmToken
    );

    @GET("profile")
    Call<Profile> showProfile();

    @Multipart
    @POST("edit/profile")
    Call<Profile> saveProfile(
            @Part("name")RequestBody name,
            @Part("email")RequestBody email
    );

    @GET("getLokasi")
    Call<List<Lokasi>> getLokasi();

    @GET("getJenis")
    Call<List<Jenis>> getJenis();

    @GET("resep/show")
    Call<List<Resep>> showResep();

    @FormUrlEncoded
    @POST("resep/search")
    Call<List<HasilCari>> showCari(
            @Field("nama_resep") String nama_resep,
            @Field("lokasi_id") String lokasi_id,
            @Field("jenis_id") String jenis_id
    );

    @GET("resep/favorite/show")
    Call<List<Favorite>> showFavorite();

    @Multipart
    @POST("resep/add")
    Call<ResponseBody> addResep(
            @Part MultipartBody.Part gambar,
            @PartMap Map<String, RequestBody> text
    );

    @Multipart
    @POST("resep/update")
    Call<ResponseBody> updateResep(
            @Part MultipartBody.Part gambar,
            @PartMap Map<String, RequestBody> text
    );

    @FormUrlEncoded
    @POST("resep/delete")
    Call<ResponseBody> deleteResep(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("resep/publish")
    Call<ResponseBody> publishResep(
            @Field("id")String id
    );

    @FormUrlEncoded
    @POST("resep/unpublish")
    Call<ResponseBody> unpublishResep(
            @Field("id")String id
    );

    @FormUrlEncoded
    @POST("resep/favorite/add")
    Call<ResponseBody> addFavorite(
            @Field("id")String id
    );

    @FormUrlEncoded
    @POST("resep/favorite/delete")
    Call<ResponseBody> deleteFavorite(
            @Field("id")String id
    );

}
