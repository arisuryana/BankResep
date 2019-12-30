package com.example.resep.ui.cari;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resep.APIHelper.ApiClient;
import com.example.resep.APIHelper.ApiService;
import com.example.resep.R;
import com.example.resep.bantuan.ConstantURL;
import com.example.resep.model.Jenis;
import com.example.resep.model.Lokasi;
import com.example.resep.ui.resep.ResepViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CariFragment extends Fragment {

    protected EditText etJudul;
    protected Spinner spinnerLokasi;
    protected Spinner spinnerJenis;
    protected Button btnCari;
    ProgressDialog loading;

    private List<Lokasi> lokasiList = new ArrayList<>();
    private List<Jenis> jenisList = new ArrayList<>();
    int id_lokasi, id_jenis;

    ApiService service;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        cariViewModel =
//                ViewModelProviders.of(this).get(CariViewModel.class);

        View view = inflater.inflate(R.layout.fragment_cari, container, false);

        etJudul = view.findViewById(R.id.et_cariJudul);
        spinnerLokasi = view.findViewById(R.id.spin_lokasi);
        spinnerJenis = view.findViewById(R.id.spin_jenis);
        btnCari = view.findViewById(R.id.btn_cariResep);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loading = ProgressDialog.show(getActivity(), null, "Loading...", true, false);
        get_Lokasi();
        get_Jenis();

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = etJudul.getText().toString();
                String lokasi_id = Integer.toString(id_lokasi);
                String jenis_id = Integer.toString(id_jenis);
                Intent intent = new Intent(getActivity(), HasilCariActivity.class);
                intent.putExtra("nama", judul);
                intent.putExtra("lokasi_id", lokasi_id);
                intent.putExtra("jenis_id", jenis_id);
                startActivity(intent);
            }
        });
    }

    private void spinnerLokasi(){
        ArrayList<String> label = new ArrayList<>();

        for (int i =0;i<lokasiList.size();i++){
            label.add(lokasiList.get(i).getNama_lokasi());
        }
        id_lokasi = lokasiList.get(0).getId();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, label);

        spinnerLokasi.setAdapter(adapter);
        spinnerLokasi.setSelection(0, true);
        View v = spinnerLokasi.getSelectedView();
        ((TextView)v).setTextColor(getResources().getColor(R.color.design_default_color_primary_dark));

        spinnerLokasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        for (int i =0;i<jenisList.size();i++){
            jenis.add(jenisList.get(i).getNama_jenis());
        }

        id_jenis = jenisList.get(0).getId();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, jenis);

        spinnerJenis.setAdapter(adapter);
        spinnerJenis.setSelection(0,true);
        View v = spinnerJenis.getSelectedView();
        ((TextView)v).setTextColor(getResources().getColor(R.color.design_default_color_primary_dark));

        spinnerJenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void get_Lokasi(){
        service = ApiClient.getService(getActivity());
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
                        Toast.makeText(getContext(), "Anda Sedang Offline", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void get_Jenis(){
        service = ApiClient.getService(getActivity());
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
                        loading.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<Jenis>> call, Throwable t) {
                        Log.e("error", t.getMessage());
                        Toast.makeText(getContext(), "Anda Sedang Offline", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }
}