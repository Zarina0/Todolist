package com.example.todolist;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todolist.room.AppDataBase;

public class App extends Application {

    AppDataBase db;
    static App app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        db= Room.databaseBuilder(getApplicationContext(),AppDataBase.class, "database")
                .allowMainThreadQueries().build();
    }

    public AppDataBase getDb() {
        return db;
    }

    public static App getApp() {
        return app;
    }
}
