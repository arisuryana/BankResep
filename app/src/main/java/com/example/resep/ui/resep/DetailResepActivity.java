package com.example.resep.ui.resep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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
import com.example.resep.MainActivity;
import com.example.resep.R;
import com.example.resep.bantuan.ConstantURL;
import com.example.resep.model.Resep;
import com.example.resep.ui.resep.editresep.EditResepActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailResepActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "id_resep";
    public static final String EXTRA_JUDUL = "judul";
    public static final String EXTRA_DESKRIPSI = "deskripsi";
    public static final String EXTRA_GAMBAR = "gambar";
    public static final String EXTRA_BAHAN = "bahan";
    public static final String EXTRA_CARA_MASAK = "cara_masak";
    public static final String EXTRA_LOKASI_ID = "lokasi_id";
    public static final String EXTRA_NAMA_LOKASI = "nama_lokasi";
    public static final String EXTRA_JENIS_ID = "jenis_id";
    public static final String EXTRA_NAMA_JENIS = "nama_jenis";
    public static final String EXTRA_TANGGAL = "created_at";

    Context context;

    TextView tvJudul, tvDeskripsi, tvTanggal, tvBahan, tvCaraMasak, tvLokasi, tvJenis;
    ImageView ivGambar;
    Button btnEdit, btnDelete;

    ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_resep);
        context = this;
        initComponents();

    }

    private void initComponents() {
        ivGambar = findViewById(R.id.img_review_photo);
        tvJudul = findViewById(R.id.tv_judulresep);
        tvTanggal = findViewById(R.id.tv_tanggalresep);
        tvDeskripsi = findViewById(R.id.tv_deskripsiresep);
        tvLokasi = findViewById(R.id.tv_lokasiresep);
        tvJenis = findViewById(R.id.tv_jenisresep);
        tvBahan = findViewById(R.id.tv_bahanresep);
        tvCaraMasak = findViewById(R.id.tv_caramasakresep);
        btnEdit = findViewById(R.id.btn_edit_resep);
        btnDelete = findViewById(R.id.btn_delete_resep);

        final String id = getIntent().getStringExtra(EXTRA_ID);
        final String nama = getIntent().getStringExtra(EXTRA_JUDUL);
        final String deskripsi = getIntent().getStringExtra(EXTRA_DESKRIPSI);
        final String gambar = getIntent().getStringExtra(EXTRA_GAMBAR);
        final String tanggal = getIntent().getStringExtra(EXTRA_TANGGAL);
        final String lokasi_id = getIntent().getStringExtra(EXTRA_LOKASI_ID);
        final String nama_lokasi = getIntent().getStringExtra(EXTRA_NAMA_LOKASI);
        final String jenis_id = getIntent().getStringExtra(EXTRA_JENIS_ID);
        final String nama_jenis = getIntent().getStringExtra(EXTRA_NAMA_JENIS);
        final String bahan = getIntent().getStringExtra(EXTRA_BAHAN);
        final String cara_masak = getIntent().getStringExtra(EXTRA_CARA_MASAK);

        namaactionbar(nama);

        tvJudul.setText(nama);
        tvTanggal.setText(tanggal);
        tvDeskripsi.setText(deskripsi);
        tvLokasi.setText(nama_lokasi);
        tvJenis.setText(nama_jenis);
        tvBahan.setText(bahan);
        tvCaraMasak.setText(cara_masak);

        Glide.with(getBaseContext())
                .load(ConstantURL.BASE_URL+gambar)
                .into(ivGambar);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailResepActivity.this, EditResepActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("judul", nama);
                intent.putExtra("deskripsi", deskripsi);
                intent.putExtra("gambar", gambar);
                intent.putExtra("nama_lokasi", nama_lokasi);
                intent.putExtra("nama_jenis", nama_jenis);
                intent.putExtra("bahan", bahan);
                intent.putExtra("cara_masak", cara_masak);
                startActivity(intent);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "id resep : "+getIntent().getStringExtra(EXTRA_ID), Toast.LENGTH_SHORT).show();
                service = ApiClient.getService(context);
                service.deleteResep(getIntent().getStringExtra(EXTRA_ID))
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Toast.makeText(context, "Berhasil Menghapus Resep", Toast.LENGTH_SHORT).show();
                                finish();
//                                Intent intent = new Intent(DetailResepActivity.this, MainActivity.class);
//                                startActivity(intent);
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
