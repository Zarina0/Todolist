package com.example.todolist.ui.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolist.App;
import com.example.todolist.R;
import com.example.todolist.adapters.HomeAdapter;
import com.example.todolist.databinding.FragmentHomeBinding;
import com.example.todolist.models.TaskModel;

import java.util.List;


public class HomeFragment extends Fragment implements HomeAdapter.Listener{
    private FragmentHomeBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClickers();
        initAdapter();

    }


    private void initAdapter() {
        App.getApp().getDb().taskDao().getData().observe(getViewLifecycleOwner(),task->{
            HomeAdapter homeAdapter = new HomeAdapter((List<TaskModel>) task,this::OnLongClick);
            binding.homeRecycler.setAdapter(homeAdapter);
        });



    }

    private void initClickers() {
        binding.openCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatTaskFragment creatTaskFragment = new CreatTaskFragment();
                creatTaskFragment.show(requireActivity().getSupportFragmentManager(),"");
            }
        });
        binding.person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireView()).navigate(R.id.profileFragment);

            }
        });
    }

    @Override
    public void OnLongClick(TaskModel model) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Удалить запись")
                .setMessage("Вы уверены, что хотите удалить эту запись?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        App.getApp().getDb().taskDao().delete(model);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }



}
