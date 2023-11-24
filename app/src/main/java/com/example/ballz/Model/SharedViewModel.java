package com.example.ballz.Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> sharedData = new MutableLiveData<>();

    public void setSharedData(String data) {
        sharedData.setValue(data);
    }

    public LiveData<String> getSharedData() {
        return sharedData;
    }
}
