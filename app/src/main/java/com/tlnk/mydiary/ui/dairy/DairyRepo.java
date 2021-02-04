package com.tlnk.mydiary.ui.dairy;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tlnk.mydiary.data.DataLoadListner;

import java.util.ArrayList;
import java.util.Queue;


/**
 * Created by Alexandr Egorshin on 04.02.2021.
 */
public class DairyRepo {

    static  DairyRepo instance;
    private ArrayList<DairyModel> dairyModels = new ArrayList<>();

    static Context mContext;
    static DataLoadListner dataLoadListner;

    public static DairyRepo getInstance(Context context) {

        mContext = context;
        if (instance == null) {
            instance = new DairyRepo();
        }
        dataLoadListner = (DataLoadListner) mContext;
        return instance;
    }

    public MutableLiveData<ArrayList<DairyModel>> getTasks() {
        loadTasks();

        MutableLiveData<ArrayList<DairyModel>> task = new MutableLiveData<>();
        task.setValue(dairyModels);

        return task;
    }

    private void loadTasks() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child("data");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot :dataSnapshot.getChildren()) {
                    dairyModels.add(snapshot.getValue(DairyModel.class));
                }
                dataLoadListner.onDairyLoaded();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
