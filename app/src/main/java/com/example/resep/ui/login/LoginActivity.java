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

import com.example.resep.APIHelper.BaseApiService;
import com.example.resep.APIHelper.UtilsApi;
import com.example.resep.AlertDialogManager;
import com.example.resep.MainActivity;
import com.example.resep.R;
import com.example.resep.SessionManagement;
import com.example.resep.ui.register.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    TextView tvRegister;
    ProgressDialog loading;

    private SharedPreferences profile;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        mApiService = UtilsApi.getApiService();

        initComponents();

        profile = getSharedPreferences("profile", Context.MODE_PRIVATE);
        final Intent toMain = new Intent(LoginActivity.this, MainActivity.class);
        if(profile.contains("id")){
            startActivity(toMain);
            finish();
        }
    }

    private void initComponents() {
        etEmail = (EditText) findViewById(R.id.txtEmail);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvRegister = (TextView) findViewById(R.id.tv_signup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean anyError = false;
                if(etEmail.getText().toString().equals("")){
                    etEmail.setError("Email tidak Boleh Kosong");
                    anyError = true;
                }

                if(etPassword.getText().toString().equals("")){
                    etPassword.setError("Password tidak Boleh Kosong");
                    anyError = true;
                }

                if(!anyError){
                    loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                    requestLogin();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterActivity.class));
                finish();
            }
        });
    }

    private void requestLogin() {
        mApiService.loginRequest(etEmail.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            loading.dismiss();
                            try{
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    // Jika login berhasil, data nama akan di parsing ke activity selanjutnya
                                    Toast.makeText(mContext, "Berhasil Login", Toast.LENGTH_SHORT).show();
                                    int id = jsonRESULTS.getJSONObject("user").getInt("id");
                                    Log.d("debug", "id : "+id);

                                    //Simpan ke Shared Preferences
                                    SharedPreferences profile = getSharedPreferences("profile", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor profileEditor = profile.edit();
                                    profileEditor.putInt("id", id);
                                    profileEditor.apply();

                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    intent.putExtra("id", id);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    //Jika Login Gagal
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e){
                                e.printStackTrace();
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure : ERROR > "+ t.toString());
                        loading.dismiss();
                    }
                });
    }
}
