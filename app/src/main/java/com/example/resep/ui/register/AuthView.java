package com.example.resep.ui.register;

import com.example.resep.model.User;

public interface AuthView {
    void onSuccess(User user);
    void onError();
    void onFailure(Throwable t);
}
