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
                " a thing- we tace care of it", R.drawable.illustration, "Skip"));
        list.add(new ViewPagerModel("Collaboration" +
                "with students", "You can create a common study schedule," +
                "group tasks, manage them and communicate " +
                "with other members", R.drawable.illustration2, "Skip"));
        list.add(new ViewPagerModel("How to keep up" +
                "with everything", "You can track your,see yuor results" +
                "and progress.Also,this is a great opportunity" +
                "to track know much time you spend studying!", R.drawable.illustration3, "Get started"));
        return list;
    }
}
