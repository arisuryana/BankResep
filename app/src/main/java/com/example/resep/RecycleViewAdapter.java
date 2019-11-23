package com.example.resep;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    private static final String TAG = "RecycleViewAdapter";
    private ArrayList<String> mImageName = new ArrayList<>();
    private ArrayList<Integer> mImage = new ArrayList<>();
    private ArrayList<String> mImageDesc = new ArrayList<>();
    private static Context mContext;

    public RecycleViewAdapter(Context mContext, ArrayList<String> mImageName, ArrayList<Integer> mImage, ArrayList<String> mImageDesc) {
        this.mImageName = mImageName;
        this.mImage = mImage;
        this.mImageDesc = mImageDesc;
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public ViewHolder onCreateViewHolder(@Nullable ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrecycle, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@Nullable ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

//        Glide.with(mContext)
//                .asBitmap()
//                .load(mImage.get(position))
//                .into(holder.gambar);
        holder.judul.setText(mImageName.get(position));
        holder.keterangan.setText(mImageDesc.get(position));
        holder.gambar.setImageResource(mImage.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG, "onClick: Kepencet on: "+ mImageName.get(position));
//                Toast.makeText(mContext, "AAAAAAAA", Toast.LENGTH_SHORT).show();
//                Toast.makeText(mContext, mImageName.get(position), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mImageName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView gambar;
        TextView judul;
        TextView keterangan;
        RelativeLayout parentLayout;
        public ViewHolder(@Nullable View itemView) {
            super(itemView);
            gambar=itemView.findViewById(R.id.resep);
            judul=itemView.findViewById(R.id.judul);
            keterangan=itemView.findViewById(R.id.desc);
            parentLayout=itemView.findViewById(R.id.relativeLayout);
        }
    }
}
