package com.example.oaa.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.oaa.R;
import com.example.oaa.domain.entity.MenuItem;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.VH> {
    private final List<MenuItem> items;

    public MenuAdapter(List<MenuItem> items) {
        this.items = items;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_profile_menu, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        MenuItem item = items.get(position);
        holder.title.setText(item.title);
        holder.icon.setImageResource(item.iconRes);
        holder.itemView.setOnClickListener(v -> {
            if (item.action != null) item.action.run();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;

        VH(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.img_icon);
            title = itemView.findViewById(R.id.tv_title);
        }
    }
}
