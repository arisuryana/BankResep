package com.example.resep;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    private static final String TAG = "RecycleViewAdapter";
    private ArrayList<String> mImageName = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> mImageDesc = new ArrayList<>();
    private Context mContext;

    public RecycleViewAdapter(ArrayList<String> mImageName, ArrayList<String> mImage, ArrayList<String> mImageDesc, Context mContext) {
        this.mImageName = mImageName;
        this.mImage = mImage;
        this.mImageDesc = mImageDesc;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrecycle, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gambar;
        TextView judul;
        TextView keterangan;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gambar=itemView.findViewById(R.id.resep);
            judul=itemView.findViewById(R.id.judul);
            keterangan=itemView.findViewById(R.id.desc);
            parentLayout=itemView.findViewById(R.id.relativeLayout);
        }
    }
}
