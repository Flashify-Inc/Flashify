package com.example.flashify;

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
    int index;
    Category category;
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
        category = (Category) getIntent().getParcelableExtra("category");
        Bundle extras = getIntent().getExtras();
        index = extras.getInt("ind");
        Log.d("IND: ", String.valueOf(index ));


        // view the flashcard
        refreshView();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category.deleteFlashcard(index);
                if (index == category.getFlashcards().size()) {
                    index = index-1;
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

                    index = index+1;
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

                index = index-1;
                refreshView();
            }
        });
    }

    public void launchMainActivity (View V){
        Intent intent = new Intent (this,MainActivity.class);
        startActivity(intent);
    }

    private void refreshView() {
        if( index == 0 ) {
            prevf.setVisibility(View.INVISIBLE);
        } else if (index+1 > category.getFlashcards().size() - 1 ){
            {nextf.setVisibility(View.INVISIBLE);}
        }

        FlashC.setText(category.getFlashcards().get(index).getFront());
        FlashC.setTextOff(category.getFlashcards().get(index).getFront());
        FlashC.setTextOn(category.getFlashcards().get(index).getBack());

    }

}