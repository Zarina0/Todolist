package com.example.todolist.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.boarding.fragment.MainBoardFragment;
import com.example.todolist.databinding.FragmentBoardBinding;
import com.example.todolist.listener.OnItemClickListener;
import com.example.todolist.models.ViewPagerModel;

import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder> {
    ArrayList<ViewPagerModel> list ;
    OnItemClickListener listener;

    public ViewPagerAdapter(ArrayList<ViewPagerModel> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }



    @NonNull
    @Override
    public ViewPagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPagerHolder(FragmentBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerHolder holder, int position) {
        holder.onBind(list.get(position));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewPagerHolder extends RecyclerView.ViewHolder {
        private FragmentBoardBinding binding;
        public ViewPagerHolder(@NonNull FragmentBoardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(ViewPagerModel model) {
            binding.tvTitle.setText(model.getTitle());
            binding.tvDiscription.setText(model.getDescription());
            binding.lottieAnim.setAnimation(model.getImage());
            binding.skipBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.itemListener();
                }
            });

        }
    }

}
