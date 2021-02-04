package com.tlnk.mydiary.ui.dairy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tlnk.mydiary.MainActivity;
import com.tlnk.mydiary.R;
import com.tlnk.mydiary.data.DataLoadListner;

import java.util.ArrayList;

/**
 * Created by Alexandr Egorshin on 03.02.2021.
 */
public class DairyFragment extends Fragment implements DataLoadListner {

    private View view;
    private RecyclerView recyclerView;
    private DairyAdapter dairyAdapter;
    private DairyViewModel dairyViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dairy, null);
        configureRecyclerView(view);
        return view;
    }

    private void configureRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.dairyListView);
        recyclerView.setAdapter(dairyAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        dairyViewModel = ViewModelProviders.of(getActivity()).get(DairyViewModel.class);
        dairyViewModel.init(getActivity().getApplicationContext());

        dairyAdapter = new DairyAdapter(dairyViewModel.getTasks().getValue());
    }

    @Override
    public void onDairyLoaded() {
        dairyViewModel.getTasks().observe(this, new Observer<ArrayList<DairyModel>>() {
            @Override
            public void onChanged(ArrayList<DairyModel> dairyModels) {
                dairyAdapter.notifyDataSetChanged();
            }
        });
    }
}
