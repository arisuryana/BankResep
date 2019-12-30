package com.example.resep.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resep.APIHelper.ApiClient;
import com.example.resep.APIHelper.BaseApiService;
import com.example.resep.APIHelper.UtilsApi;
import com.example.resep.MainActivity;
import com.example.resep.R;
import com.example.resep.bantuan.PreferencesHelper;
import com.example.resep.model.User;
import com.example.resep.ui.register.AuthPresenter;
import com.example.resep.ui.register.AuthView;
import com.example.resep.ui.register.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements AuthView, View.OnClickListener {
    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    TextView tvRegister;
    ProgressDialog loading;

    PreferencesHelper preferencesHelper;
    AuthPresenter presenter;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        preferencesHelper=new PreferencesHelper(mContext);

        Intent intent;
        intent = new Intent(LoginActivity.this, MainActivity.class);
        if(preferencesHelper.getLogin()){
            startActivity(intent);
            finish();
        }

        initComponents();

    }

    private void initComponents() {
        etEmail = (EditText) findViewById(R.id.txtEmail);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvRegister = (TextView) findViewById(R.id.tv_signup);

        btnLogin.setOnClickListener(this);
        presenter = new AuthPresenter(this, ApiClient.getService(mContext));

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btnLogin:
                login();
                break;
        }
    }

    @Override
    public void onSuccess(User user) {
        preferencesHelper.setUserLogin(user);
        startActivity(new Intent(this, MainActivity.class));
        finish();
        Toast.makeText(mContext, "Login Berhasil", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(mContext, "Failed :"+t, Toast.LENGTH_SHORT).show();
    }

    public void login(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (validate(email, password)){
            presenter.login(email, password);
        }
    }

    public boolean validate(String email, String password){
        if (email.equals("")){
            etEmail.setError("Field email tidak boleh kosong");
            return false;
        }

        if (password.equals("")){
            etPassword.setError("Field password tidak boleh kosong");
            return false;
        }

        return true;
    }




}
