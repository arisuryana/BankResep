package com.example.resep.ui.dashboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resep.APIHelper.ApiClient;
import com.example.resep.APIHelper.ApiService;
import com.example.resep.R;
import com.example.resep.bantuan.DbHelper;
import com.example.resep.model.Favorite;
import com.example.resep.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends HomeFragment{

//    private DashboardViewModel dashboardViewModel;
    private RecyclerView recyclerView;
    ProgressDialog loading;
    ApiService service;
    private FavoriteAdapter adapter;
    private List<Favorite> favoriteList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel =
//                ViewModelProviders.of(this).get(DashboardViewModel.class);
        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = view.findViewById(R.id.rv_favorite);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        loading = ProgressDialog.show(getActivity(), null, "Loading...", true, false);
        loadfavorite();
    }

    private void loadfavorite() {
        service = ApiClient.getService(getActivity());
        service.showFavorite()
                .enqueue(new Callback<List<Favorite>>() {
                    @Override
                    public void onResponse(Call<List<Favorite>> call, Response<List<Favorite>> response) {
                        if (response.isSuccessful()){
                            favoriteList = response.body();
                            Log.d("debug", "isi : "+favoriteList);

                            DbHelper dbHelper = new DbHelper(getContext());
                            dbHelper.deleteFavorite();

                            for (Favorite favorites:favoriteList){
                                dbHelper.insertFavorite(favorites.getId(), favorites.getUsers_id(), favorites.getResep_id(),
                                        favorites.getNama_pemilik(), favorites.getNama_resep(), favorites.getDeskripsi(),
                                        favorites.getGambar(), favorites.getBahan(), favorites.getCara_masak(),
                                        favorites.getNama_lokasi(), favorites.getNama_jenis(), favorites.getCreated_at());
                            }

                            adapter = new FavoriteAdapter(getActivity(), favoriteList);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        }else {

                        }
                        loading.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<Favorite>> call, Throwable t) {
                        Log.e("error", t.getMessage());
                        Toast.makeText(getContext(), "Anda Sedang Offline", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                        callFavoriteLocal();
                    }
                });
    }

    private void callFavoriteLocal() {
        DbHelper dbHelper = new DbHelper(getContext());
        favoriteList = dbHelper.selectFavorite();

        adapter = new FavoriteAdapter(getContext(), favoriteList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}