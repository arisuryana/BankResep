package com.example.resep.ui.resep.editresep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.resep.APIHelper.ApiClient;
import com.example.resep.APIHelper.ApiService;
import com.example.resep.MainActivity;
import com.example.resep.R;
import com.example.resep.bantuan.ConstantURL;
import com.example.resep.bantuan.PermissionHelper;
import com.example.resep.model.Jenis;
import com.example.resep.model.Lokasi;
import com.example.resep.ui.resep.DetailResepActivity;
import com.example.resep.ui.resep.ResepFragment;
import com.example.resep.ui.resep.addresep.AddResepActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditResepActivity extends AppCompatActivity {
    Button btnBrowse, btnSave;
    Spinner spinLokasi, spinJenis;
    ImageView ivResep;
    EditText etNama, etDeskripsi, etBahan, etCaraMasak;

    public static final String EXTRA_ID = "id";
    public static final String EXTRA_JUDUL = "judul";
    public static final String EXTRA_DESKRIPSI = "deskripsi";
    public static final String EXTRA_GAMBAR = "gambar";
    public static final String EXTRA_BAHAN = "bahan";
    public static final String EXTRA_CARA_MASAK = "cara_masak";
    public static final String EXTRA_NAMA_LOKASI = "nama_lokasi";
    public static final String EXTRA_NAMA_JENIS = "nama_jenis";

    String nama_lokasi;
    String nama_jenis;

    private List<Lokasi> lokasiList = new ArrayList<>();
    private List<Jenis> jenisList = new ArrayList<>();

    int id_lokasi, id_jenis;
    MultipartBody.Part imageMB;
    private static int RESULT_LOAD_IMAGE = 1;
    String nama_resep, deskripsi, bahan, cara_masak, lokasi_id, jenis_id;

    ApiService service;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resep);

        mContext = this;

        PermissionHelper.verifyStoragePermissions(this);

        initComponents();
    }

    private void initComponents() {
        ivResep = findViewById(R.id.img_review_photo);
        etNama = findViewById(R.id.et_judulresep);
        etDeskripsi = findViewById(R.id.et_deskripsiresep);
        etBahan = findViewById(R.id.et_bahanresep);
        etCaraMasak = findViewById(R.id.et_caramasak);
        spinLokasi = findViewById(R.id.spin_lokasi);
        spinJenis = findViewById(R.id.spin_jenis);
        btnBrowse = findViewById(R.id.btn_upload_image);
        btnSave = findViewById(R.id.btn_save_edit_resep);

        final String nama = getIntent().getStringExtra(EXTRA_JUDUL);
        final String deskripsi = getIntent().getStringExtra(EXTRA_DESKRIPSI);
        final String bahan = getIntent().getStringExtra(EXTRA_BAHAN);
        final String cara_masak = getIntent().getStringExtra(EXTRA_CARA_MASAK);
        final String gambar = getIntent().getStringExtra(EXTRA_GAMBAR);
//        final String lokasi_id = getIntent().getStringExtra(EXTRA_LOKASI_ID);
//        final String jenis_id = getIntent().getStringExtra(EXTRA_JENIS_ID);
        nama_lokasi = getIntent().getStringExtra(EXTRA_NAMA_LOKASI);
        nama_jenis = getIntent().getStringExtra(EXTRA_NAMA_JENIS);
        final String id = getIntent().getStringExtra(EXTRA_ID);

        etNama.setText(nama);
        etDeskripsi.setText(deskripsi);
        etBahan.setText(bahan);
        etCaraMasak.setText(cara_masak);

        Glide.with(getBaseContext())
                .load(ConstantURL.BASE_URL+gambar)
                .into(ivResep);

        get_Lokasi();
        get_Jenis();

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(id);
            }
        });
    }

    private void save(String id) {
        nama_resep = etNama.getText().toString();
        deskripsi = etDeskripsi.getText().toString();
        bahan = etBahan.getText().toString();
        cara_masak = etCaraMasak.getText().toString();
        lokasi_id = String.valueOf(id_lokasi);
        jenis_id = String.valueOf(id_jenis);

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("id", RequestBody.create(MultipartBody.FORM, id));
        map.put("nama_resep", RequestBody.create(MultipartBody.FORM, nama_resep));
        map.put("deskripsi", RequestBody.create(MultipartBody.FORM, deskripsi));
        map.put("bahan", RequestBody.create(MultipartBody.FORM, bahan));
        map.put("cara_masak", RequestBody.create(MultipartBody.FORM, cara_masak));
        map.put("lokasi_id", RequestBody.create(MultipartBody.FORM, lokasi_id));
        map.put("jenis_id", RequestBody.create(MultipartBody.FORM, jenis_id));

        service = ApiClient.getService(mContext);
        service.updateResep(imageMB, map)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(EditResepActivity.this, "Resep Berhasil diubah ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditResepActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("error", t.getMessage());
                        Toast.makeText(mContext, "Anda Sedang Offline", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data){
            Uri selectedImage = data.getData();

            try{
                Bitmap bitmapFoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                bitmapFoto = scaleDown(bitmapFoto, true);
                ivResep.setImageBitmap(bitmapFoto);

                File file = createTempFile(bitmapFoto);
                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                imageMB = MultipartBody.Part.createFormData("gambar", file.getName(), reqFile);
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private File createTempFile(Bitmap bitmap) {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                , "image.PNG");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG,0, bos);
        byte[] bitmapdata = bos.toByteArray();
        //write the bytes in file

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private Bitmap scaleDown(Bitmap realImage, boolean filter) {
        if (realImage.getWidth() > 1500 || realImage.getHeight() > 1500){
            Bitmap newbitmap = Bitmap.createScaledBitmap(realImage, 600,
                    800, filter);
            return newbitmap;
        }
        else{
            return realImage;
        }
    }

    private void spinnerLokasi(){
        ArrayList<String> label = new ArrayList<>();
        int posisi_lokasi = 0;
        for (int i =0;i<lokasiList.size();i++){
            label.add(lokasiList.get(i).getNama_lokasi());
            if (nama_lokasi.equals(lokasiList.get(i).getNama_lokasi())){
                posisi_lokasi = i;
            }
        }

        id_lokasi = lokasiList.get(posisi_lokasi).getId();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_dropdown_item, label);

        spinLokasi.setAdapter(adapter);
        spinLokasi.setSelection(posisi_lokasi, true);
        View v = spinLokasi.getSelectedView();
        ((TextView)v).setTextColor(getResources().getColor(R.color.design_default_color_primary_dark));

        spinLokasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id_lokasi = lokasiList.get(i).getId();
                Log.d("id spiner lokasi","id : "+id_lokasi);
                ((TextView)view).setTextColor(getResources().getColor(R.color.design_default_color_primary_dark));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void spinnerJenis(){
        ArrayList<String> jenis = new ArrayList<>();
        int posisi_jenis = 0;
        for (int i =0;i<jenisList.size();i++){
            jenis.add(jenisList.get(i).getNama_jenis());
            if (nama_jenis.equals(jenisList.get(i).getNama_jenis())){
                posisi_jenis = i;
            }
        }

        id_jenis = jenisList.get(posisi_jenis).getId();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_dropdown_item, jenis);

        spinJenis.setAdapter(adapter);
        spinJenis.setSelection(posisi_jenis,true);
        View v = spinJenis.getSelectedView();
        ((TextView)v).setTextColor(getResources().getColor(R.color.design_default_color_primary_dark));

        spinJenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id_jenis = jenisList.get(i).getId();
                Log.d("id spiner jenis","id : "+id_jenis);
                ((TextView)view).setTextColor(getResources().getColor(R.color.design_default_color_primary_dark));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void get_Lokasi() {
        service = ApiClient.getService(mContext);
        service.getLokasi()
                .enqueue(new Callback<List<Lokasi>>() {
                    @Override
                    public void onResponse(Call<List<Lokasi>> call, Response<List<Lokasi>> response) {
                        if (response.isSuccessful()){
                            lokasiList.addAll(response.body());
                            spinnerLokasi();
                        } else {
                            Log.e("apaya", "Gak Berhasil");
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Lokasi>> call, Throwable t) {
                        Log.e("error", t.getMessage());
                        Toast.makeText(mContext, "Anda Sedang Offline", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void get_Jenis() {
        service = ApiClient.getService(mContext);
        service.getJenis()
                .enqueue(new Callback<List<Jenis>>() {
                    @Override
                    public void onResponse(Call<List<Jenis>> call, Response<List<Jenis>> response) {
                        if (response.isSuccessful()){
                            jenisList.addAll(response.body());
                            spinnerJenis();
                        } else {
                            Log.e("apaya", "Gak Berhasil");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Jenis>> call, Throwable t) {
                        Log.e("error", t.getMessage());
                        Toast.makeText(mContext, "Anda Sedang Offline", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void sendData() {
        Intent intent = new Intent(EditResepActivity.this, MainActivity.class);

        startActivity(intent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
