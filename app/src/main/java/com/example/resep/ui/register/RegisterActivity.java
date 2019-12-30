package com.example.resep.ui.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.resep.R;
import com.example.resep.bantuan.PreferencesHelper;
import com.example.resep.model.User;
import com.example.resep.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements AuthView, View.OnClickListener {
    EditText etName;
    EditText etEmail;
    EditText etPassword;
    EditText etCpassword;
    Button btnRegister;
    TextView tvLogin;
    ProgressDialog loading;

    private PreferencesHelper preferencesHelper;
    private AuthPresenter presenter;

    String fcmToken;

    Context mContext;
//    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this;

        initComponents();
    }

    private void initComponents() {
        etName = (EditText) findViewById(R.id.name);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        etCpassword = (EditText) findViewById(R.id.retype);
        btnRegister = (Button) findViewById(R.id.register);
        tvLogin = (TextView) findViewById(R.id.textView2);

        btnRegister.setOnClickListener(this);
        presenter = new AuthPresenter(this, ApiClient.getService(mContext));

        preferencesHelper = new PreferencesHelper(mContext);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.register:
                register();
                break;
        }
    }

    @Override
    public void onSuccess(User user){
        preferencesHelper.setUserLogin(user);
        startActivity(new Intent(mContext, LoginActivity.class));
        finish();
        Toast.makeText(mContext, "Registrasi Akun Berhasil", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(mContext, "Response Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(mContext, "Error :"+t, Toast.LENGTH_SHORT).show();
    }

    public void register() {
        final String name = etName.getText().toString();
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();
        final String cpassword = etCpassword.getText().toString();

        if(validate(name, email, password, cpassword)){
//            loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()){
                                Log.w("TOKEN", "getInstanced Failed", task.getException());
                                return;
                            }
                            fcmToken = task.getResult().getToken();
                            presenter.register(name, email, password, cpassword, fcmToken);
                            Log.e("token", fcmToken);
                        }
                    });

        }
    }

    public boolean validate(String name, String email, String password, String cpassword){
        if (name.equals("")){
            etName.setError("Field Nama Tidak Boleh Kosong");
            return false;
        }

        if (email.equals("")){
            etEmail.setError("Field E-Mail Tidak Boleh Kosong");
            return false;
        }

        if (password.equals("")){
            etPassword.setError("Field Password Tidak Boleh Kosong");
            return false;
        }

        if (cpassword.equals("")){
            etCpassword.setError("Field Retype Password Tidak Boleh Kosong");
            return false;
        }

        return true;
    }


}
