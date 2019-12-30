package com.example.resep.ui.notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.resep.APIHelper.ApiClient;
import com.example.resep.MainActivity;
import com.example.resep.R;
import com.example.resep.model.Profile;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileUserActivity extends AppCompatActivity {

    public static final String KEY_PROFILE = "profile";

    EditText etName, etEmail;
    Button btnSave;

    Profile profile;
    Context mContext;

    MultipartBody.Part body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_user);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mContext = this;
        init();
        setView();
    }

    private void setView() {
        etName.setText(profile.getName());
        etEmail.setText(profile.getEmail());

    }

    private void init() {
        profile = getIntent().getParcelableExtra(KEY_PROFILE);

        etName = findViewById(R.id.etValueName);
        etEmail = findViewById(R.id.etValueEmail);

        btnSave = findViewById(R.id.btnSaveProfile);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan();
            }
        });
    }

    private void simpan() {
        RequestBody name = RequestBody.create(MultipartBody.FORM, etName.getText().toString());
        RequestBody email = RequestBody.create(MultipartBody.FORM, etEmail.getText().toString());

        ApiClient.getService(this)
                .saveProfile(name, email)
                .enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if (response.isSuccessful()){
                            Intent intent = new Intent(EditProfileUserActivity.this, MainActivity.class);
                            intent.putExtra("userProfile", ProfileUserFragment.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(mContext,"Berhasil Mengubah Profile", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(mContext,"Response Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                        Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
