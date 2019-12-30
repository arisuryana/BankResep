package com.example.resep.ui.resep;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.resep.APIHelper.ApiClient;
import com.example.resep.APIHelper.ApiService;
import com.example.resep.CustomOnItemClickListener;
import com.example.resep.R;
import com.example.resep.bantuan.ConstantURL;
import com.example.resep.model.Resep;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResepAdapter extends RecyclerView.Adapter<ResepAdapter.ViewHolder> {
    private Context context;
    private View.OnClickListener onClickListener;
    private List<Resep> reseps = new ArrayList<>();
    ApiService service;

    public ResepAdapter(Context context, List<Resep> reseps){
        this.context = context;
        this.reseps = reseps;
    }

    public interface OnClickListener{
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_resep, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d("debug", "Isi adapter : "+reseps.get(position).getGambar());
        holder.tvNamaResep.setText(reseps.get(position).getNama_resep());
        holder.tvTanggal.setText(reseps.get(position).getCreated_at());
        holder.tvLokasi.setText(reseps.get(position).getNama_lokasi());
        holder.tvJenis.setText(reseps.get(position).getNama_jenis());
        if (context != null){
            Glide.with(context)
                    .load(ConstantURL.BASE_URL+reseps.get(position).getGambar())
                    .into(holder.imgResep);
        }

        holder.btnDetail.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Detail "+reseps.get(position).getNama_resep(), Toast.LENGTH_SHORT).show();
                Log.d("debug id", "id yg di click : "+reseps.get(position).getId());

                Intent intent = new Intent(context, DetailResepActivity.class);
                intent.putExtra("id_resep", reseps.get(position).getId());
                intent.putExtra("judul", reseps.get(position).getNama_resep());
                intent.putExtra("deskripsi", reseps.get(position).getDeskripsi());
                intent.putExtra("gambar", reseps.get(position).getGambar());
                intent.putExtra("bahan", reseps.get(position).getBahan());
                intent.putExtra("cara_masak", reseps.get(position).getCara_masak());
                intent.putExtra("lokasi_id", reseps.get(position).getLokasi_id());
                intent.putExtra("nama_lokasi", reseps.get(position).getNama_lokasi());
                intent.putExtra("jenis_id", reseps.get(position).getJenis_id());
                intent.putExtra("nama_jenis", reseps.get(position).getNama_jenis());
                intent.putExtra("created_at", reseps.get(position).getCreated_at());
                context.startActivity(intent);
            }
        });

        final String flags = reseps.get(position).getPublish();
        if (flags.equals("0")){
            holder.btnPublish.setText("Publish");
        }else if (flags.equals("1")){
            holder.btnPublish.setText("Unpublish");
        }
        holder.btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flags.equals("0")){
                    //code to publish->1
                    service = ApiClient.getService(context);
                    service.publishResep(reseps.get(position).getId())
                            .enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    Toast.makeText(context, "Berhasil di Publish Resep", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.e("error", t.getMessage());
                                    Toast.makeText(context, "Anda Sedang Offline", Toast.LENGTH_SHORT).show();
                                }
                            });
                }else if (flags.equals("1")) {
                    //code to unpublish->0
                    //inget notifyDataSetChanged();
                    service = ApiClient.getService(context);
                    service.unpublishResep(reseps.get(position).getId())
                            .enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    Toast.makeText(context, "Berhasil di Unpublish Resep", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.e("error", t.getMessage());
                                    Toast.makeText(context, "Anda Sedang Offline", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (reseps != null) ? reseps.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgResep;
        Button btnDetail, btnPublish;
        TextView tvNamaResep, tvTanggal, tvLokasi, tvJenis;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgResep = itemView.findViewById(R.id.img_item_photo);
            tvNamaResep = itemView.findViewById(R.id.tv_item_judul);
            tvTanggal = itemView.findViewById(R.id.tv_item_tanggal);
            tvLokasi = itemView.findViewById(R.id.tv_item_lokasi);
            tvJenis = itemView.findViewById(R.id.tv_item_jenis);
            btnDetail = itemView.findViewById(R.id.btn_set_detail);
            btnPublish = itemView.findViewById(R.id.btn_set_publish);
        }
    }
}
