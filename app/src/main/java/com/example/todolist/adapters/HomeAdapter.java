package com.example.todolist.adapters;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.databinding.ItemTaskBinding;
import com.example.todolist.models.TaskModel;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    List<TaskModel> list;
    Listener listener;

    public HomeAdapter(List<TaskModel> list, Listener listener) {
        this.list = list;
        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void delete(TaskModel model) {
        list.remove(model);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class HomeHolder extends RecyclerView.ViewHolder {
        private ItemTaskBinding binding;

        public HomeHolder(@NonNull ItemTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void onBind(TaskModel taskModel ) {
            binding.titleTv.setText(taskModel.getTask());
            binding.dateTv.setText(taskModel.getDate());
            binding.repeatTv.setText(taskModel.getRepeat());
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.OnLongClick(taskModel);
                    return false;
                }
            });
        }
    }
    public interface Listener{
        void OnLongClick(TaskModel model);
    }

}

