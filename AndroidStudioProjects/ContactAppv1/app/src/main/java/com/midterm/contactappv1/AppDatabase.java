package com.midterm.contactappv1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1 )
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDAO contactDAO();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "contacts").build();
        }
        return instance;
    }
}
