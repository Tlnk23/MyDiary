package com.tlnk.mydiary.ui.taskCreate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tlnk.mydiary.R;

/**
 * Created by Alexandr Egorshin on 03.02.2021.
 */
public class TaskCreateFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_task_create, null);
        return view;
    }
}
