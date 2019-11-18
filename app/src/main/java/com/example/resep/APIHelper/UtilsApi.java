package com.example.resep.APIHelper;

import retrofit2.Retrofit;

public class UtilsApi {
    //10.0.2.2 ip localhost
    public static final String BASE_URL_API = "https://10.0.0.2/api/";

    //Deklarasi BaseApiService
    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
