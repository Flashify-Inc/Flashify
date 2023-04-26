package com.example.flashify;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class FlashcardView extends AppCompatActivity {
    ToggleButton FlashC;
    int flag2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_view);

        Bundle extras = getIntent().getExtras();
        flag2 = extras.getInt("flag2");
        FlashC = findViewById(R.id.FlachCD);

        if(flag2 == 1){
            Category c = (Category) getIntent().getParcelableExtra("co");
            FlashC.setText(c.getFlashcards().get(0).getFront());
            FlashC.setTextOff(c.getFlashcards().get(0).getFront());
            FlashC.setTextOn(c.getFlashcards().get(0).getBack());
        }

        if(flag2 == 2){
            Category c = (Category) getIntent().getParcelableExtra("co");
            FlashC.setText(c.getFlashcards().get(1).getFront());
            FlashC.setTextOff(c.getFlashcards().get(1).getFront());
            FlashC.setTextOn(c.getFlashcards().get(1).getBack());
        }

    }



}