package com.example.resep.ui.cari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.resep.APIHelper.ApiClient;
import com.example.resep.APIHelper.ApiService;
import com.example.resep.R;
import com.example.resep.model.HasilCari;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilCariActivity extends AppCompatActivity {
    public static final String EXTRA_JUDUL = "nama";
    public static final String EXTRA_LOKASI_ID = "lokasi_id";
    public static final String EXTRA_JENIS_ID = "jenis_id";

    private RecyclerView recyclerView;
    ProgressDialog loading;
    private HasilCariAdapter adapter;
    private List<HasilCari> hasilCariList = new ArrayList<>();

    Context context;
    ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_cari);
        context = this;
        recyclerView = findViewById(R.id.rv_resep);

        loadhasil();
    }

    private void loadhasil() {
        String nama_resep = getIntent().getStringExtra(EXTRA_JUDUL);
        String lokasi_id = getIntent().getStringExtra(EXTRA_LOKASI_ID);
        String jenis_id = getIntent().getStringExtra(EXTRA_JENIS_ID);

        Log.d("asik", "nama : "+nama_resep+"lokasi id : "+lokasi_id+"jenis id : "+jenis_id);
        loading = ProgressDialog.show(context, null, "Loading...", true, false);
        service = ApiClient.getService(context);
        service.showCari(nama_resep, lokasi_id, jenis_id)
                .enqueue(new Callback<List<HasilCari>>() {
                    @Override
                    public void onResponse(Call<List<HasilCari>> call, Response<List<HasilCari>> response) {
                        if (response.isSuccessful()){
                            hasilCariList = response.body();
                            Log.d("debug", "isi : "+hasilCariList);
                            adapter = new HasilCariAdapter(context, hasilCariList);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        }else {

                        }
                        loading.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<HasilCari>> call, Throwable t) {
                        Log.e("error", t.getMessage());
                        Toast.makeText(context, "Anda Sedang Offline", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }


}
