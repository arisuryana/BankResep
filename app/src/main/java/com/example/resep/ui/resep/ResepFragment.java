package com.example.resep.ui.resep;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.resep.R;
import com.example.resep.RecycleViewAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ResepFragment extends Fragment {

    private static final String TAG = "ResepFragment";

    private ResepViewModel resepViewModel;
    private ArrayList<Integer> mImage = new ArrayList<>();
    private ArrayList<String> mImageName = new ArrayList<>();
    private ArrayList<String> mImagesDesc = new ArrayList<>();
    private Context context;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Starto.");
        resepViewModel =
                ViewModelProviders.of(this).get(ResepViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_resep, container, false);
        initImageBitmap();
        Log.d(TAG, "initRecyclerView: Recycler Nyala");
        RecyclerView recyclerView = root.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        RecycleViewAdapter adapter = new RecycleViewAdapter(context, mImageName, mImage, mImagesDesc);
        recyclerView.setAdapter(adapter);
        return root;

    }

    public void initImageBitmap() {
        if (getView() == null) {
            mImageName.add("Gambar1");
            mImagesDesc.add("GAMMMMBAR");
            mImage.add(R.drawable.profil);
            return;
        } else {
            Log.d(TAG, "initImageBitmap: preparing bitmaps.");


        }

//    public void initRecyclerView(){


//
//    }
    }
}