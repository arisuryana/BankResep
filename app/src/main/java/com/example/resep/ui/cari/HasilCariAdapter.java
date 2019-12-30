package com.example.resep.ui.cari;

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
import com.example.resep.model.HasilCari;
import com.example.resep.ui.resep.ResepAdapter;

import java.util.ArrayList;
import java.util.List;

public class HasilCariAdapter extends RecyclerView.Adapter<HasilCariAdapter.ViewHolder> {
    private Context context;
    private View.OnClickListener onClickListener;
    private List<HasilCari> hasilCariList = new ArrayList<>();

    public HasilCariAdapter(Context context, List<HasilCari> hasilCariList){
        this.context = context;
        this.hasilCariList = hasilCariList;
    }

    public interface OnClickListener{
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_hasilcari, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d("debug", "Isi adapter : "+hasilCariList.get(position).getGambar());
        holder.tvNamaResep.setText(hasilCariList.get(position).getNama_resep());
        holder.tvAdmin.setText(hasilCariList.get(position).getName());
        holder.tvTanggal.setText(hasilCariList.get(position).getCreated_at());
        holder.tvLokasi.setText(hasilCariList.get(position).getNama_lokasi());
        holder.tvJenis.setText(hasilCariList.get(position).getNama_jenis());
        if (context != null){
            Glide.with(context)
                    .load(ConstantURL.BASE_URL+hasilCariList.get(position).getGambar())
                    .into(holder.imgResep);
        }

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Detail "+hasilCariList.get(position).getNama_resep(), Toast.LENGTH_SHORT).show();
                Log.d("debug id", "id yg di click : "+hasilCariList.get(position).getId());

                Intent intent = new Intent(context, DetailCariActivity.class);
                intent.putExtra("id", hasilCariList.get(position).getId());
                intent.putExtra("name", hasilCariList.get(position).getName());
                intent.putExtra("judul", hasilCariList.get(position).getNama_resep());
                intent.putExtra("deskripsi", hasilCariList.get(position).getDeskripsi());
                intent.putExtra("gambar", hasilCariList.get(position).getGambar());
                intent.putExtra("bahan", hasilCariList.get(position).getBahan());
                intent.putExtra("cara_masak", hasilCariList.get(position).getCara_masak());
                intent.putExtra("lokasi_id", hasilCariList.get(position).getLokasi_id());
                intent.putExtra("nama_lokasi", hasilCariList.get(position).getNama_lokasi());
                intent.putExtra("jenis_id", hasilCariList.get(position).getJenis_id());
                intent.putExtra("nama_jenis", hasilCariList.get(position).getNama_jenis());
                intent.putExtra("created_at", hasilCariList.get(position).getCreated_at());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (hasilCariList != null) ? hasilCariList.size() : 0;
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
