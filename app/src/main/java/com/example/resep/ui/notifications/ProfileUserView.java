package com.example.resep.ui.notifications;

import com.example.resep.model.Profile;

public interface ProfileUserView {
    void showLoading();
    void hideLoading();
    void onSuccess(Profile profile);
    void onError();
    void onFailure(Throwable t);
}
