package com.example.parcialappsmoviles;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

public class AppDataBase {


    @Database(entities = {Auto.class}, version = 3)

    public abstract static class AppDatabase extends RoomDatabase {
        public abstract AutoDao autoDao();


        public static AppDatabase INSTANCE;

        static AppDatabase getInstance(final Context context) {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context,
                        AppDatabase.class, "database-name")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }

            return INSTANCE;
        }
    }
}
