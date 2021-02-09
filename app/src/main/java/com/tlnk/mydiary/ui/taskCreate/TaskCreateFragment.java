package com.tlnk.mydiary.ui.taskCreate;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tlnk.mydiary.MainActivity;
import com.tlnk.mydiary.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Alexandr Egorshin on 03.02.2021.
 */
public class TaskCreateFragment extends Fragment {
    private View view;
    private EditText editName, editDescription, editHoursStart, editMinutesStart, editHoursFinish, editMinutesFinish, editDate;
    private Button submitButton;
    private int mDay, mMonth, mYear;
    public DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_task_create, null);
        init();

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                mDay = calendar.get(Calendar.DATE);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editDate.setText(dayOfMonth + "." + month + "."+year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPushClick(v);

            }
        });

        return view;
    }

    private void init() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Data");
        submitButton = view.findViewById(R.id.add_button);
        editName = view.findViewById(R.id.add_task_name);
        editDescription = view.findViewById(R.id.add_task_description);
        editHoursStart = view.findViewById(R.id.add_task_hour_start);
        editMinutesStart = view.findViewById(R.id.add_task_minute_start);
        editHoursFinish = view.findViewById(R.id.add_task_hour_finish);
        editMinutesFinish= view.findViewById(R.id.add_task_minute_finish);
        editDate = view.findViewById(R.id.add_task_date);
    }

    public void onPushClick(View view) {
        String name = editName.getText().toString();
        String description = editDescription.getText().toString();

        String hoursStart = (editHoursStart.getText().toString());
        String minutesStart = (editMinutesStart.getText().toString());
        String hoursFinish = (editHoursFinish.getText().toString());
        String minutesFinish = (editMinutesFinish.getText().toString());
        int startHours = Integer.parseInt(hoursStart);
        int startMinutes = Integer.parseInt(minutesStart);
        int finishHours = Integer.parseInt(hoursFinish);
        int finishMinutes = Integer.parseInt(minutesFinish);


        Calendar dateStart = Calendar.getInstance();
        Calendar dateFinish = Calendar.getInstance();

        dateStart.set(mYear, mMonth, mDay, startHours, startMinutes);
        dateFinish.set(mYear, mMonth, mDay, finishHours, finishMinutes);

        long startMills = dateStart.getTimeInMillis();
        long finishMills = dateFinish.getTimeInMillis();

        TaskCreateModel taskCreateModel = new TaskCreateModel(name, description, startMills, finishMills);
        databaseReference.push().setValue(taskCreateModel);
    }
}
