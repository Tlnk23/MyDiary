package com.tlnk.mydiary.ui.dairy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tlnk.mydiary.R;

import java.util.ArrayList;

/**
 * Created by Alexandr Egorshin on 04.02.2021.
 */
public class DairyAdapter extends RecyclerView.Adapter<DairyAdapter.TaskViewHolder> {

    private ArrayList<DairyModel> dairyModels;

    public DairyAdapter(ArrayList<DairyModel> dairyModels) {
        this.dairyModels = dairyModels;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dairy_item_view, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.itemView.setTag(dairyModels.get(position));
        holder.taskName.setText(dairyModels.get(position).getTaskName());
        holder.taskTime.setText(dairyModels.get(position).getTaskTime());
    }

    @Override
    public int getItemCount() {
        return dairyModels.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskName;
        TextView taskTime;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            taskName = itemView.findViewById(R.id.taskName);
            taskTime = itemView.findViewById(R.id.taskTime);
        }
    }
}
