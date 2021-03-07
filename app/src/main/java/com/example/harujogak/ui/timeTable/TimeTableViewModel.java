package com.example.harujogak.ui.timeTable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimeTableViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TimeTableViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is time table fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}