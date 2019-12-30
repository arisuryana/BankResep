package com.example.resep.ui.notifications;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.resep.APIHelper.ApiClient;
import com.example.resep.APIHelper.BaseApiService;
import com.example.resep.APIHelper.UtilsApi;
import com.example.resep.R;
import com.example.resep.bantuan.DbHelper;
import com.example.resep.bantuan.PreferencesHelper;
import com.example.resep.model.Profile;
import com.example.resep.ui.login.LoginActivity;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileUserFragment extends Fragment implements ProfileUserView{

//    private NotificationsViewModel notificationsViewModel;
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable
//                             ViewGroup container, Bundle savedInstanceState) {
//        notificationsViewModel =
//                ViewModelProviders.of(this).get(NotificationsViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_profile, container, false);
//
//        final TextView textView = root.findViewById(R.id.text_profile);
//        notificationsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//
//        return root;
//    }

    protected Button btnLogout, btnEditProfile;
    protected TextView tvProfileName;
    protected TextView tvProfileEmail;

    protected ProfileUserPresenter presenter;

    Profile profile;
    private PreferencesHelper preferencesHelper;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
//        notificationsViewModel =
//                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        tvProfileName = view.findViewById(R.id.tv_valueName);
        tvProfileEmail = view.findViewById(R.id.tv_valueEmail);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        preferencesHelper = new PreferencesHelper(getContext());

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");

        presenter = new ProfileUserPresenter(this, ApiClient.getService(getContext()));
        presenter.showProfile();

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(EditProfileUserActivity.KEY_PROFILE, profile);
                Intent intent = new Intent(getContext(), EditProfileUserActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencesHelper.logout();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.hide();
    }

    @Override
    public void onSuccess(Profile profile) {
        DbHelper dbHelper = new DbHelper(getContext());
        dbHelper.deleteProfile();

        this.profile = profile;
        dbHelper.insertProfile(profile.getId(), profile.getName(), profile.getEmail());

        tvProfileName.setText(profile.getName());
        tvProfileEmail.setText(profile.getEmail());
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), "Response Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getContext(), "Anda Sedang Offline", Toast.LENGTH_SHORT).show();
        showProfileLocal();
    }

    private void showProfileLocal(){
        DbHelper dbHelper=new DbHelper(getContext());
        profile=dbHelper.selectProfile();

        tvProfileName.setText(profile.getName());
        tvProfileEmail.setText(profile.getEmail());
    }



}