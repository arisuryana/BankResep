package com.example.resep.ui.resep;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.resep.APIHelper.ApiClient;
import com.example.resep.APIHelper.ApiService;
import com.example.resep.R;
import com.example.resep.model.Resep;
import com.example.resep.ui.resep.addresep.AddResepActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResepFragment extends Fragment {
//    private ResepViewModel resepViewModel;
    private RecyclerView recyclerView;
    protected FloatingActionButton fab_add;
    ProgressDialog loading;
    ApiService service;
    private ResepAdapter adapter;
    private List<Resep> resepArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        resepViewModel =
//                ViewModelProviders.of(this).get(ResepViewModel.class);
        final View view = inflater.inflate(R.layout.fragment_resep, container, false);

        fab_add = view.findViewById(R.id.fab_add);
        recyclerView = view.findViewById(R.id.rv_resep);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        loading = ProgressDialog.show(getActivity(), null, "Loading...", true, false);

        loadresep();
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddResepActivity.class);
                startActivity(intent);
            }
        });

    }


    private void loadresep() {
        service = ApiClient.getService(getActivity());
        service.showResep()
                .enqueue(new Callback<List<Resep>>() {
                    @Override
                    public void onResponse(Call<List<Resep>> call, Response<List<Resep>> response) {
                        if (response.isSuccessful()){
                            resepArrayList = response.body();
                            Log.d("debug", "isi : "+resepArrayList);
                            adapter = new ResepAdapter(getActivity(), resepArrayList);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        }else {

                        }
                        loading.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<Resep>> call, Throwable t) {
                        Log.e("error", t.getMessage());
                        Toast.makeText(getContext(), "Anda Sedang Offline", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }

}