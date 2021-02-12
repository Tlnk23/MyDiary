package com.tlnk.mydiary.ui.dairy;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DairyViewModel extends ViewModel {

    private MutableLiveData<ArrayList<DairyModel>> liveData;
    private DairyRepo dairyRepo;

    public void init (long timeStart, long timeFinish) {
        if (liveData!=null) {
            return;
        }

        liveData = DairyRepo.getInstance().getNames(timeStart, timeFinish);
    }

    public LiveData<ArrayList<DairyModel>> getLiveData() {
        return liveData;
    }


}
