package com.tlnk.mydiary.ui.dairy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tlnk.mydiary.R;

import java.util.ArrayList;

public class DairyAdapter extends RecyclerView.Adapter<DairyAdapter.DairyViewHolder> {

    private ArrayList<DairyModel> dairyModels;

    public DairyAdapter(ArrayList<DairyModel> dairyModels) {
        this.dairyModels = dairyModels;
    }

    @NonNull
    @Override
    public DairyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dairy_item_view, parent, false);
        return new DairyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DairyViewHolder holder, int position) {
        holder.itemView.setTag(dairyModels.get(position));
        holder.name.setText(dairyModels.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dairyModels.size();
    }

    class DairyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        public DairyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.taskName);
        }
    }
}
