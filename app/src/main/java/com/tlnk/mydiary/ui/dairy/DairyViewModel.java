package com.tlnk.mydiary.ui.dairy;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

/**
 * Created by Alexandr Egorshin on 04.02.2021.
 */
public class DairyViewModel  extends ViewModel {

    MutableLiveData<ArrayList<DairyModel>> tasks;

    public void init(Context context) {
        if (tasks != null) {
            return;
        }

        tasks = DairyRepo.getInstance(context).getTasks();
    }

    public LiveData<ArrayList<DairyModel>> getTasks() {
        return tasks;
    }

}
