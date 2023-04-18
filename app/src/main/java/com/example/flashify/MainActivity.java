package com.example.flashify;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashify.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());


        Category untitledCategory = new Category("Untitled_Category");
        db.categoryDao().insertCategory(untitledCategory);

        Category mathCategory = new Category("Mathematics");
        db.categoryDao().insertCategory(mathCategory);

        Category csCategory = new Category("Computer_Science");
        db.categoryDao().insertCategory(csCategory);

        Log.d("debug", "whatever");


        List<Category> allCategories = db.categoryDao().getAllCategories();

            for (int i = 0; i < allCategories.size(); i++){
            Log.d("debug", String.valueOf(allCategories.get(i).categoryName));
        }

        //Flashcard curFlashcard = new Flashcard("Manzana", "Apple", 0);
        //db.flashcardsDao().insertAll(curFlashcard);

                /*
        binding.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                flFront = binding.editTextTextPersonName.getText().toString();
                flBack = binding.editTextTextPersonName2.getText().toString();

                Flashcard fl1 = new Flashcard(flFront, flBack, 1234);

                db.flashcardsDao().insertAll(fl1);

            }
        });

                 */


    }

}