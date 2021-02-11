package com.tlnk.mydiary.ui.dairy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tlnk.mydiary.MainActivity;
import com.tlnk.mydiary.R;
import com.tlnk.mydiary.ui.taskCreate.TaskCreateFragment;
import com.tlnk.mydiary.ui.taskDescription.TaskDescriptionFragment;

import java.util.ArrayList;

/**
 * Created by Alexandr Egorshin on 03.02.2021.*
 **/

public class DairyFragment extends Fragment{

    private View view;
    private RecyclerView recyclerView;
    private DairyAdapter dairyAdapter;
    private DairyViewModel dairyViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dairy, null);

        recyclerView = view.findViewById(R.id.dairyListView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        loadData();

        return view;
    }

    private void taskClickListner() {
        dairyAdapter.dairyAdapterClick = new DairyAdapterClick() {
            @Override
            public void onTaskClick(DairyModel dairyModel) {
                ((MainActivity)getActivity()).changeFragment(((MainActivity)getActivity()).newInstance(dairyModel));
            }
        };
    }

    public void loadData() {
        dairyViewModel = ViewModelProviders.of(getActivity()).get(DairyViewModel.class);
        dairyViewModel.init();
        dairyViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<DairyModel>>() {
            @Override
            public void onChanged(ArrayList<DairyModel> dairyModels) {
                dairyAdapter.notifyDataSetChanged();
            }
        });
        dairyAdapter = new DairyAdapter(dairyViewModel.getLiveData().getValue());
        taskClickListner();
        recyclerView.setAdapter(dairyAdapter);
    }

}
