package com.tlnk.mydiary.ui.dairy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tlnk.mydiary.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DairyAdapter extends RecyclerView.Adapter<DairyAdapter.DairyViewHolder> {

    private ArrayList<DairyModel> dairyModels;

    public void setDairyAdapterClick(DairyAdapterClick dairyAdapterClick) {
        this.dairyAdapterClick = dairyAdapterClick;
    }

    public DairyAdapterClick dairyAdapterClick;

    public DairyAdapter(ArrayList<DairyModel> dairyModels) {
        this.dairyModels = dairyModels;
    }

    @NonNull
    @Override
    public DairyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dairy_item_view, parent, false);
        return new DairyViewHolder(view, dairyAdapterClick);
    }

    @Override
    public void onBindViewHolder(@NonNull DairyViewHolder holder, int position) {
        holder.itemView.setTag(dairyModels.get(position));
        holder.name.setText(dairyModels.get(position).getName());

        holder.time.setText(setTime(dairyModels.get(position).getDate_start(), dairyModels.get(position).getDate_finish()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dairyAdapterClick != null) {
                    dairyAdapterClick.onTaskClick(dairyModels.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dairyModels.size();
    }

    class DairyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView time;
        DairyAdapterClick dairyAdapterClick;

        public DairyViewHolder(@NonNull View itemView, DairyAdapterClick dairyAdapterClick) {
            super(itemView);

            this.dairyAdapterClick = dairyAdapterClick;

            name = itemView.findViewById(R.id.taskName);
            time = itemView.findViewById(R.id.taskTime);
        }
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
