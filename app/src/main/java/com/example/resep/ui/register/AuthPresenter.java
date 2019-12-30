package com.example.resep.ui.register;

import android.app.ProgressDialog;

import com.example.resep.APIHelper.ApiService;
import com.example.resep.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthPresenter {
    private AuthView view;
    private ApiService service;
    ProgressDialog loading;

    public AuthPresenter(AuthView view, ApiService service){
        this.view = view;
        this.service = service;
    }

    public void login(String email, String password){
        service.login(email, password)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
//                            loading.dismiss();
                            view.onSuccess(response.body());
                        }else{
                            view.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
//                        loading.dismiss();
                        view.onFailure(t);
                    }
                });
    }

    public void register(String name, String email, String password, String cpassword, String fcmToken){
        service.register(name, email, password, cpassword, fcmToken)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
//                            loading.dismiss();
                            view.onSuccess(response.body());
                        }else {
                            view.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
//                        loading.dismiss();
                        view.onFailure(t);
                    }
                });
    }
}
