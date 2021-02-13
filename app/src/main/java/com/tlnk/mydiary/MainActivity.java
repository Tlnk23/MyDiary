package com.tlnk.mydiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tlnk.mydiary.ui.dairy.DairyFragment;
import com.tlnk.mydiary.ui.dairy.DairyModel;
import com.tlnk.mydiary.ui.taskCreate.TaskCreateFragment;
import com.tlnk.mydiary.ui.taskDescription.TaskDescriptionFragment;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    private LinearLayout dateButton;
    private int mDay, mMonth, mYear;

    private FloatingActionButton fab;

    public DateClickListener dateClickListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.main_toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
//        toolbarTitle.setText(dataSet());

        setDateClickListener(dateClickListener);

        setSupportActionBar(toolbar);

        changeFragment(new DairyFragment());

        dateButton = findViewById(R.id.section_date);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                mDay = calendar.get(Calendar.DATE);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        toolbarTitle.setText(dayOfMonth + "." + month + "."+year);
                        dateClickListener.dateClick(dayOfMonth + "." + month + "."+year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new TaskCreateFragment());
                toolbar.setVisibility(View.INVISIBLE);
                dateButton.setVisibility(View.GONE);
                fab.hide();
            }
        });
    }

    public void changeFragment(Fragment targetfragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentView, targetfragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    private String dataSet() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        return dateText;
    }

    public void showTools() {
        fab.show();
        toolbar.setVisibility(View.VISIBLE);
        dateButton.setVisibility(View.VISIBLE);
    }

    public String getDate() {
        return toolbarTitle.getText().toString();
    }

    public TaskDescriptionFragment newInstance(DairyModel dairyModel) {
        TaskDescriptionFragment f = new TaskDescriptionFragment();
        Bundle args = new Bundle();
        args.putSerializable("dairyModel", dairyModel);
        f.setArguments(args);
        return f;
    }

    public void setDateClickListener(DateClickListener dateClickListener) {
        this.dateClickListener = dateClickListener;
    }

    public long getTimeStart() {
        String str = toolbarTitle.getText().toString();
        ParsePosition pp1 = new ParsePosition(0);
        Date date=new SimpleDateFormat("dd.MM.yyyy").parse(str, pp1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.getTimeInMillis();
    }

    public long getTimeFinish() {
        String str = toolbarTitle.getText().toString();
        ParsePosition pp1 = new ParsePosition(0);
        Date date=new SimpleDateFormat("dd.MM.yyyy").parse(str, pp1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTimeInMillis();
    }
}