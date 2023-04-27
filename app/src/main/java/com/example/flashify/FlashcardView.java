package com.example.flashify;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class FlashcardView extends AppCompatActivity {
    ToggleButton FlashC;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_view);

        FlashC = findViewById(R.id.FlachCD);

            Category c = (Category) getIntent().getParcelableExtra("co");
            Bundle extras = getIntent().getExtras();
            int index = extras.getInt("ind");

            FlashC.setText(c.getFlashcards().get(index).getFront());
            FlashC.setTextOff(c.getFlashcards().get(index).getFront());
            FlashC.setTextOn(c.getFlashcards().get(index).getBack());

    }



}