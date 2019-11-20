package com.example.resep.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.resep.R;
import com.example.resep.SessionManagement;

public class NotificationsFragment extends Fragment  implements View.OnClickListener{

    private NotificationsViewModel notificationsViewModel;
    Button logout;
    SessionManagement session;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView textView = root.findViewById(R.id.text_profile);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        logout=root.findViewById(R.id.btnLogout);
        logout.setOnClickListener(this);
        session = new SessionManagement(getActivity().getApplicationContext());
        return root;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogout:
                session.logoutUser();
                break;
            default:
                break;
        }
    }
}