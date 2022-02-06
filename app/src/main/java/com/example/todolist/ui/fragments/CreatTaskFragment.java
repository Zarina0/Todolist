package com.example.todolist.ui.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.RadioButton;

import com.example.todolist.App;
import com.example.todolist.R;
import com.example.todolist.databinding.FragmentCreatTaskBinding;
import com.example.todolist.models.TaskModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import io.grpc.internal.LogExceptionRunnable;


public class CreatTaskFragment extends BottomSheetDialogFragment {


    FragmentCreatTaskBinding binding;
    private int startYear;
    private int starthMonth;
    private int startDay;
    private  String date;
    private String repeat;
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreatTaskBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClickers();
    }

    private void initClickers() {
        binding.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToDataBase();
                datareturn();
                dismiss();
            }
        });

        binding.tvChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        binding.tvChooseRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRepeatDialog();
            }
        });

    }

    private void writeToDataBase(){
        String text = binding.edText.getText().toString();
        TaskModel taskModel = new TaskModel(text,date, repeat);
        App.getApp().getDb().taskDao().insert(taskModel);
        Map<String, String> task = new HashMap<>();
        task.put("task", taskModel.getTask());
        task.put("date", taskModel.getDate());
        task.put("repeat", taskModel.getRepeat());

        db.collection("task")
                .add(task)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
    private void datareturn() {
        db.collection("task").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        Map<String, Object> tasks = documentSnapshot.getData();
                        Log.e("ololo","onComplete:" + "\n"
                                + tasks.get("task")+ "\n"+
                                tasks.get("date") + "\n"
                                + tasks.get("repeat"));
                    }
                }
                else {
                    Log.e("ololo", "onComplete: "+ task.getException() );
                }
            }
        });
    }


    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        startYear = calendar.get(Calendar.YEAR);
        starthMonth = calendar.get(Calendar.MONTH);
        startDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(), this::onDateSet, startYear, starthMonth, startDay);
        datePickerDialog.show();
    }

    private void showRepeatDialog() {
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.repeat_dialog, null);
        Dialog alertDialog = new Dialog(requireContext());
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(view);
        alertDialog.show();
        RadioButton never = alertDialog.findViewById(R.id.never_radioBtn);
        RadioButton everyDay = alertDialog.findViewById(R.id.every_day_radioBtn);
        RadioButton everyWeek = alertDialog.findViewById(R.id.every_week_radioBtn);
        RadioButton everyMonth = alertDialog.findViewById(R.id.every_month_radioBtn);
        RadioButton everyYear = alertDialog.findViewById(R.id.every_year_radioBtn);
        never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String never = "Never";
                binding.tvChooseRepeat.setText(never);
                repeat=never;
                alertDialog.dismiss();
            }
        });
        everyDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String everyDay = "Every Day";
                binding.tvChooseRepeat.setText(everyDay);
                repeat= everyDay;
                alertDialog.dismiss();
            }
        });
        everyWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String everyWeek = "Every Week";
                binding.tvChooseRepeat.setText(everyWeek);
                repeat=everyWeek;
                alertDialog.dismiss();
            }
        });
        everyMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String everyMonth = "Every Month";
                binding.tvChooseRepeat.setText(everyMonth);
                repeat= everyMonth;
                alertDialog.dismiss();
            }
        });
        everyYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String everyYear = "Every Year";
                binding.tvChooseRepeat.setText(everyYear);
                repeat= everyYear;
                alertDialog.dismiss();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        date = "" + day + "." + month + 1 + "." + year;
        binding.tvChooseDate.setText("" + day + "." + month + 1 + "." + year);
    }
}