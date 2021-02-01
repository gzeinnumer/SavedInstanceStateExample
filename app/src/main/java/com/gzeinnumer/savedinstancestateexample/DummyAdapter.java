package com.gzeinnumer.savedinstancestateexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DummyAdapter extends RecyclerView.Adapter<DummyAdapter.MyHolder> {

    OnItemClickListener onItemClickListener;
    List<String> list;

    public DummyAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.list = new ArrayList<>();
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //holder.itemView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), GblVariabel.anim));
        holder.itemView.setOnClickListener(view -> {
            onItemClickListener.onItemClick(position);
        });

        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }
    }
}
