package com.example.flashify;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class FlashcardView extends AppCompatActivity {
    ToggleButton FlashC;
    int index;
    ImageButton nextf, prevf;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_view);

        // localize the interactive buttons in the screen
        FlashC = findViewById(R.id.FlachCD);
        nextf = findViewById(R.id.nextF);
        prevf = findViewById(R.id.prevF);

        // retrieve the data
        Category c = (Category) getIntent().getParcelableExtra("categoryNumber");
        Bundle extras = getIntent().getExtras();
        index = extras.getInt("ind");
        Log.d("IND: ", String.valueOf(index ));


        // view the flashcard
        FlashC.setText(c.getFlashcards().get(index).getFront());
        FlashC.setTextOff(c.getFlashcards().get(index).getFront());
        FlashC.setTextOn(c.getFlashcards().get(index).getBack());

        if( index+1 > c.getFlashcards().size() - 1 )
            {nextf.setVisibility(View.INVISIBLE);}

        if( index == 0 )
            {prevf.setVisibility(View.INVISIBLE);}

            // next button

            nextf.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (FlashC.isChecked())
                            FlashC.toggle();

                        prevf.setVisibility(View.VISIBLE);

                        index = index+1;
                        FlashC.setText(c.getFlashcards().get(index).getFront());
                        FlashC.setTextOff(c.getFlashcards().get(index).getFront());
                        FlashC.setTextOn(c.getFlashcards().get(index).getBack());

                        if( index+1 > c.getFlashcards().size() -1 )
                            nextf.setVisibility(View.INVISIBLE);
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
                        FlashC.setText(c.getFlashcards().get(index).getFront());
                        FlashC.setTextOff(c.getFlashcards().get(index).getFront());
                        FlashC.setTextOn(c.getFlashcards().get(index).getBack());

                        if( index == 0 )
                            prevf.setVisibility(View.INVISIBLE);
                    }
                });


    }

    public void launchMainActivity (View V){
        Intent intent = new Intent (this,MainActivity.class);
        startActivity(intent);
    }

}