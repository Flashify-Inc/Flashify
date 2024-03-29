package com.example.flashify;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FlashcardDB.class, CategoryDB.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    private static final String PRELOADED_DATABASE_FILE = "my-preloaded-database.db";
    private static final String DB_NAME = "my-database.db";
    private static volatile AppDatabase instance;

    public abstract FlashcardsDao flashcardsDao();
    public abstract CategoryDao categoryDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                .createFromAsset(PRELOADED_DATABASE_FILE)
                .allowMainThreadQueries()
                .build();
    }


}
