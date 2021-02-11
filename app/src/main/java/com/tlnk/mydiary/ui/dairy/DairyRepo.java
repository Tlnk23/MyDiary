package com.tlnk.mydiary.ui.dairy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DairyRepo {

    static DairyRepo instance;
    private ArrayList<DairyModel> dairyModels = new ArrayList<>();
    private MutableLiveData<ArrayList<DairyModel>> name = new MutableLiveData<>();

    public static DairyRepo getInstance() {

        if (instance == null) {
            instance = new DairyRepo();
        }

        return instance;
    }

    public MutableLiveData<ArrayList<DairyModel>> getNames() {
        if (dairyModels.size() == 0) {
            loadNames();
        }
        name.setValue(dairyModels);
        return name;
    }

    private void loadNames() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("Data").orderByValue();
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                dairyModels.add(snapshot.getValue(DairyModel.class));
                name.postValue(dairyModels);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                dairyModels.add(snapshot.getValue(DairyModel.class));
                name.postValue(dairyModels);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                dairyModels.add(snapshot.getValue(DairyModel.class));
                name.postValue(dairyModels);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
