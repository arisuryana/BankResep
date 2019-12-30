package com.example.resep.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.resep.APIHelper.ApiClient;
import com.example.resep.APIHelper.ApiService;
import com.example.resep.R;
import com.example.resep.bantuan.ConstantURL;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFavoriteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_JUDUL = "judul";
    public static final String EXTRA_PEMILIK = "nama_pemilik";
    public static final String EXTRA_DESKRIPSI = "deskripsi";
    public static final String EXTRA_GAMBAR = "gambar";
    public static final String EXTRA_BAHAN = "bahan";
    public static final String EXTRA_CARA_MASAK = "cara_masak";
    public static final String EXTRA_NAMA_LOKASI = "nama_lokasi";
    public static final String EXTRA_NAMA_JENIS = "nama_jenis";
    public static final String EXTRA_TANGGAL = "created_at";

    Context context;
    TextView tvJudul, tvPemilik, tvDeskripsi, tvTanggal, tvBahan, tvCaraMasak, tvLokasi, tvJenis;
    ImageView ivGambar;
    Button btnUnfavorite;
    ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorite);
        context = this;
        initComponents();
    }

    private void initComponents() {
        ivGambar = findViewById(R.id.img_review_photo);
        tvJudul = findViewById(R.id.tv_judulresep);
        tvPemilik = findViewById(R.id.tv_adminresep);
        tvTanggal = findViewById(R.id.tv_tanggalresep);
        tvDeskripsi = findViewById(R.id.tv_deskripsiresep);
        tvLokasi = findViewById(R.id.tv_lokasiresep);
        tvJenis = findViewById(R.id.tv_jenisresep);
        tvBahan = findViewById(R.id.tv_bahanresep);
        tvCaraMasak = findViewById(R.id.tv_caramasakresep);
        btnUnfavorite = findViewById(R.id.btn_remove_favorite);

        final String id = getIntent().getStringExtra(EXTRA_ID);
        final String nama_pemilik = getIntent().getStringExtra(EXTRA_PEMILIK);
        final String judul = getIntent().getStringExtra(EXTRA_JUDUL);
        final String deskripsi = getIntent().getStringExtra(EXTRA_DESKRIPSI);
        final String gambar = getIntent().getStringExtra(EXTRA_GAMBAR);
        final String tanggal = getIntent().getStringExtra(EXTRA_TANGGAL);
        final String nama_lokasi = getIntent().getStringExtra(EXTRA_NAMA_LOKASI);
        final String nama_jenis = getIntent().getStringExtra(EXTRA_NAMA_JENIS);
        final String bahan = getIntent().getStringExtra(EXTRA_BAHAN);
        final String cara_masak = getIntent().getStringExtra(EXTRA_CARA_MASAK);

        namaactionbar(judul);

        tvJudul.setText(judul);
        tvPemilik.setText(nama_pemilik);
        tvTanggal.setText(tanggal);
        tvDeskripsi.setText(deskripsi);
        tvLokasi.setText(nama_lokasi);
        tvJenis.setText(nama_jenis);
        tvBahan.setText(bahan);
        tvCaraMasak.setText(cara_masak);

        Glide.with(getBaseContext())
                .load(ConstantURL.BASE_URL+gambar)
                .into(ivGambar);

        btnUnfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter to add unfavorite
                service = ApiClient.getService(context);
                service.deleteFavorite(id)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Toast.makeText(context, "Resep "+judul+"Berhasil di Hapus", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.e("error", t.getMessage());
                                Toast.makeText(context, "Anda Sedang Offline", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void namaactionbar(String title){
        getSupportActionBar().setTitle(title);
    }
}
