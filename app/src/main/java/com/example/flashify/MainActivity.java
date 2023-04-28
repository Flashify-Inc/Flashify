package com.example.flashify;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashify.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getInstance(getApplicationContext());


        db.flashcardsDao().deleteAllFlashcards();
        db.categoryDao().deleteAllCategories();

        CategoryDB curCategory1DB = new CategoryDB("Math");
        long curCategory1Id = curCategory1DB.getCategoryId();
        FlashcardDB curFlashcard1DB = new FlashcardDB("5 + 5 = ? ", "10", curCategory1Id);
        //Flashcard curFlashcard2 = new Flashcard("5 * 10 = ? ", "50", 13);

        db.categoryDao().insertCategory(curCategory1DB);
        db.flashcardsDao().insertAll(curFlashcard1DB);

        List<CategoryDB> allCategories = db.categoryDao().getAllCategories();
            for (int i = 0; i < allCategories.size(); i++){
            Log.d("debug", String.valueOf(allCategories.get(i).categoryName));
        }


    }

}