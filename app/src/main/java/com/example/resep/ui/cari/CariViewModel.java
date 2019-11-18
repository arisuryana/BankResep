package com.example.resep.ui.cari;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CariViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CariViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is cari fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}