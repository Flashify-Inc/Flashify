package com.example.flashify;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CategoryView extends AppCompatActivity {
    TextView catText;
    Button fbtn1, fbtn2, fbtn3, fbtn4 ;
    Switch edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);


        // localize the interactive buttons in the screen
            catText = findViewById(R.id.textCategoryView);
            // Flashcard buttons
            ArrayList<Button> FshButtons = new ArrayList<>();
            fbtn1 = findViewById(R.id.BtnFlashcard1);
            fbtn2 = findViewById(R.id.btnFlashcard2);
            fbtn3 = findViewById(R.id.btnFlashcard3);
            fbtn4 = findViewById(R.id.btnFlashcard4);
            FshButtons.add(fbtn1);
            FshButtons.add(fbtn2);
            FshButtons.add(fbtn3);
            FshButtons.add(fbtn4);

/************************* INITIALIZER *****************************/

            // retrieve the category object from the previous activity
            Category c = (Category) getIntent().getParcelableExtra("co");
            catText.setText(c.getName());

            // initialize the fsh buttons
            for (int id = 0; id < c.getFlashcards().size() ; id++){
                FshButtons.get(id).setText(c.getFlashcards().get(id).getFront());
                FshButtons.get(id).setVisibility(View.VISIBLE);
                int finalId = id;
                FshButtons.get(id).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intentF = new Intent (CategoryView.this, FlashcardView.class);
                        intentF.putExtra("co",c);
                        int index = finalId;
                        intentF.putExtra("ind",index);
                        startActivity(intentF);
                    }
                });

            }


/******************************************************************/
        edit = findViewById(R.id.editBtn2);

        ArrayList<ImageButton> dlts = new ArrayList<ImageButton>();
        // delete buttons
        dlts.add(findViewById(R.id.dltf1));
        dlts.add(findViewById(R.id.dltf2));
        dlts.add(findViewById(R.id.dltf3));
        dlts.add(findViewById(R.id.dltf4));

        //edit category buttons
        ArrayList<ImageButton> Renameflashcards = new ArrayList<ImageButton>();
        Renameflashcards.add(findViewById(R.id.rn1));
        Renameflashcards.add(findViewById(R.id.rn2));
        Renameflashcards.add(findViewById(R.id.rn3));
        Renameflashcards.add(findViewById(R.id.rn4));
;

        /********* edit toggle ************/

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isEditOn = edit.isChecked();
                // toggle on
                if (isEditOn) {
                    for (int dor=0; dor<c.getFlashcards().size(); dor++){
                        dlts.get(dor).setVisibility(View.VISIBLE);
                        Renameflashcards.get(dor).setVisibility(View.VISIBLE);
                    }
                    // toggle off
                } else {
                    for (int biha=0; biha<c.getFlashcards().size(); biha++){
                        dlts.get(biha).setVisibility(View.INVISIBLE);
                        Renameflashcards.get(biha).setVisibility(View.INVISIBLE);
                    }

                }
            }
        });
    }

}