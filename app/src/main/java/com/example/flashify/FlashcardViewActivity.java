package com.example.flashify;

import static com.example.flashify.MainActivity.categories;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class FlashcardViewActivity extends AppCompatActivity {
    ToggleButton FlashC;
    int flashcardInd;
    int categoryInd;
    ImageButton nextf, prevf, deleteButton, editButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_view);

        // localize the interactive buttons in the screen
        FlashC = findViewById(R.id.FlachCD);
        nextf = findViewById(R.id.nextF);
        prevf = findViewById(R.id.prevF);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);

        // retrieve the data
        categoryInd = getIntent().getIntExtra("categoryInd", 0);
        Bundle extras = getIntent().getExtras();
        flashcardInd = extras.getInt("flashcardInd");

        // view the flashcard
        refreshView();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categories.get(categoryInd).deleteFlashcard(flashcardInd);
                if (flashcardInd == categories.get(categoryInd).getFlashcards().size()) {
                    flashcardInd = flashcardInd-1;
                } ;
                refreshView();
            }
        });

        // next button
        nextf.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (FlashC.isChecked())
                        FlashC.toggle();

                    prevf.setVisibility(View.VISIBLE);

                    flashcardInd = flashcardInd+1;
                    refreshView();
                }

        });

        // previous button
        prevf.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FlashC.isChecked())
                    FlashC.toggle();

                nextf.setVisibility(View.VISIBLE);

                flashcardInd = flashcardInd-1;
                refreshView();
            }
        });
    }

    public void launchMainActivity (View V){
        Intent intent = new Intent (this,MainActivity.class);
        startActivity(intent);
    }

    private void refreshView() {
        if( flashcardInd == 0 ) {
            prevf.setVisibility(View.INVISIBLE);
        } else if (flashcardInd+1 > categories.get(categoryInd).getFlashcards().size() - 1 ){
            {nextf.setVisibility(View.INVISIBLE);}
        }

        FlashC.setText(categories.get(categoryInd).getFlashcards().get(flashcardInd).getFront());
        FlashC.setTextOff(categories.get(categoryInd).getFlashcards().get(flashcardInd).getFront());
        FlashC.setTextOn(categories.get(categoryInd).getFlashcards().get(flashcardInd).getBack());

    }

}