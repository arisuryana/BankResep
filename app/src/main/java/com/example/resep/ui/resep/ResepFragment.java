package com.example.resep.ui.resep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.resep.R;
import com.example.resep.ui.notifications.NotificationsViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class ResepFragment extends Fragment {

    private ResepViewModel resepViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        resepViewModel =
                ViewModelProviders.of(this).get(ResepViewModel.class);
        View root = inflater.inflate(R.layout.fragment_resep, container, false);
        final TextView textView = root.findViewById(R.id.text_resep);
        resepViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}