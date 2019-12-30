package com.example.resep.ui.dashboard;

import android.content.Context;
import android.content.Intent;
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
import com.example.resep.R;
import com.example.resep.bantuan.ConstantURL;
import com.example.resep.model.Favorite;
import com.example.resep.ui.resep.ResepAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private Context context;
    private View.OnClickListener onClickListener;
    private List<Favorite> favoriteList = new ArrayList<>();

    public FavoriteAdapter(Context context, List<Favorite> favoriteList){
        this.context = context;
        this.favoriteList = favoriteList;
    }

    public interface OnClickListener{
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_favorite, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d("debug", "Isi adapter : "+favoriteList.get(position).getGambar());
        holder.tvNamaResep.setText(favoriteList.get(position).getNama_resep());
        holder.tvAdmin.setText(favoriteList.get(position).getNama_pemilik());
        holder.tvTanggal.setText(favoriteList.get(position).getCreated_at());
        holder.tvLokasi.setText(favoriteList.get(position).getNama_lokasi());
        holder.tvJenis.setText(favoriteList.get(position).getNama_jenis());
        if (context != null){
            Glide.with(context)
                    .load(ConstantURL.BASE_URL+favoriteList.get(position).getGambar())
                    .into(holder.imgResep);
        }

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Detail "+favoriteList.get(position).getNama_resep(), Toast.LENGTH_SHORT).show();
                Log.d("debug id", "id yg di click : "+favoriteList.get(position).getId());

                Intent intent = new Intent(context, DetailFavoriteActivity.class);
                intent.putExtra("id", favoriteList.get(position).getId());
                intent.putExtra("nama_pemilik", favoriteList.get(position).getNama_pemilik());
                intent.putExtra("judul", favoriteList.get(position).getNama_resep());
                intent.putExtra("deskripsi", favoriteList.get(position).getDeskripsi());
                intent.putExtra("gambar", favoriteList.get(position).getGambar());
                intent.putExtra("bahan", favoriteList.get(position).getBahan());
                intent.putExtra("cara_masak", favoriteList.get(position).getCara_masak());
                intent.putExtra("nama_lokasi", favoriteList.get(position).getNama_lokasi());
                intent.putExtra("nama_jenis", favoriteList.get(position).getNama_jenis());
                intent.putExtra("created_at", favoriteList.get(position).getCreated_at());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (favoriteList != null) ? favoriteList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgResep;
        Button btnDetail;
        TextView tvNamaResep, tvAdmin, tvTanggal, tvLokasi, tvJenis;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgResep = itemView.findViewById(R.id.img_item_photo);
            tvNamaResep = itemView.findViewById(R.id.tv_item_judul);
            tvAdmin = itemView.findViewById(R.id.tv_item_admin);
            tvTanggal = itemView.findViewById(R.id.tv_item_tanggal);
            tvLokasi = itemView.findViewById(R.id.tv_item_lokasi);
            tvJenis = itemView.findViewById(R.id.tv_item_jenis);
            btnDetail = itemView.findViewById(R.id.btn_set_detail);
        }
    }
}
