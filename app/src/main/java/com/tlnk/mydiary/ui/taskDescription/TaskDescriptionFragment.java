package com.tlnk.mydiary.ui.taskDescription;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tlnk.mydiary.R;
import com.tlnk.mydiary.ui.dairy.DairyModel;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Alexandr Egorshin on 03.02.2021.
 */
public class TaskDescriptionFragment extends Fragment {
    private View view;
    private TextView name, description, time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_task_description, null);

        Bundle args = getArguments();
        DairyModel dairyModel = (DairyModel) args.getSerializable("dairyModel");

        init(view);

        name.setText(dairyModel.getName());
        description.setText(dairyModel.getDescription());
        time.setText(setTime(dairyModel.getDate_start(), dairyModel.getDate_finish()));

        return view;
    }

    private void init(View view) {
        name = view.findViewById(R.id.task_name);
        description = view.findViewById(R.id.task_description);
        time = view.findViewById(R.id.task_time);
    }

    private String setTime(long millisStart, long millisFinish) {
        Calendar dateStart = Calendar.getInstance();
        Calendar dateFinish = Calendar.getInstance();

        dateStart.setTimeInMillis(millisStart);
        dateFinish.setTimeInMillis(millisFinish);

        String finalDate = dateStart.get(Calendar.HOUR_OF_DAY) + ":" + dateStart.get(Calendar.MINUTE) + " - " + dateFinish.get(Calendar.HOUR_OF_DAY) + ":" + dateFinish.get(Calendar.MINUTE);

        return (finalDate);
    }
}
