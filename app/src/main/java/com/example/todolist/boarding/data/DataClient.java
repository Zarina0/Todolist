package com.example.todolist.boarding.data;

import com.example.todolist.R;
import com.example.todolist.models.ViewPagerModel;

import java.util.ArrayList;

public class DataClient {
    public static ArrayList<ViewPagerModel> list = new ArrayList<>();


    public static ArrayList<ViewPagerModel> getData() {
        list.add(new ViewPagerModel("Welcome " +
                "to productive life!", "You can plan your time,add and organize " +
                "tasks.Reminders so that you don't forget" +
                " a thing- we tace care of it", R.raw.animation1, "Continue"));
        list.add(new ViewPagerModel("Collaboration" +
                "with students", "You can create a common study schedule," +
                "group tasks, manage them and communicate " +
                "with other members", R.raw.chat, "Continue"));
        list.add(new ViewPagerModel("How to keep up" +
                "with everything", "You can track your,see yuor results" +
                "and progress.Also,this is a great opportunity" +
                "to track know much time you spend studying!", R.raw.update, "Continue"));
        return list;
    }
}
