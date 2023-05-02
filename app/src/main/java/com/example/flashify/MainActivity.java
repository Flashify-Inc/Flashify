package com.example.flashify;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton addBtn, manualBtn, magicBtn;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // localize the interactive buttons in the screen
            addBtn=findViewById(R.id.openBtn);
            magicBtn=findViewById(R.id.magicBtn);
            manualBtn=findViewById(R.id.manualBtn);

            // category buttons
                ArrayList<Button> catbuttons = new ArrayList<>();
        Button categoryBtn1 = findViewById(R.id.btnCategory1);
        Button categoryBtn2 = findViewById(R.id.btnCategory2);
        Button categoryBtn3 = findViewById(R.id.btnCategory3);
        Button categoryBtn4 = findViewById(R.id.btnCategory4);
        Button categoryBtn5 = findViewById(R.id.btnCategory5);
                catbuttons.add(categoryBtn1);
                catbuttons.add(categoryBtn2);
                catbuttons.add(categoryBtn3);
                catbuttons.add(categoryBtn4);
                catbuttons.add(categoryBtn5);

        // display magic & manual button
        addBtn.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onClick(View V) {
                        if (magicBtn.getVisibility() == View.VISIBLE){
                            magicBtn.setVisibility(View.INVISIBLE);
                            manualBtn.setVisibility(View.INVISIBLE);
                        }
                        else {
                            magicBtn.setVisibility(View.VISIBLE);
                            manualBtn.setVisibility(View.VISIBLE);
                        }
                    }
                }
        );

        /***************************   INITIALIZATION   ******************************/

                // DATABASE DUMMY DATA
                ArrayList<Category> database = new ArrayList<Category>();
                database.add(new Category("Geography" ));
                database.add(new Category("Chemistry" ));;
                database.add(new Category("Supreme lord emperor" ));
                database.get(0).getFlashcards().add(new Flashcard("what is the capital of Spain ?", "Madrid"));
                database.get(0).getFlashcards().add(new Flashcard ("What is the capital of Italy ?", "Rome"));
                //database.get(1).getFlashcards().add(new Flashcard ("H2O","the water chemical compound"));
                database.get(1).getFlashcards().add(new Flashcard ("dilution","the act of decreasing the concentration of a soluble"));
                database.get(2).getFlashcards().add(new Flashcard ("OBEY","obey the lord"));
                database.get(2).getFlashcards().add(new Flashcard ("DISOBEY","die"));
                database.get(2).getFlashcards().add(new Flashcard ("Angry","you also die"));


        // Category initialization
                ArrayList<Category> categories = new ArrayList<Category>();
                String TempN, TempF, TempB;

                for(int id = 0; id< database.size() ; id++) {
                    // loading category from the database
                    TempN = database.get(id).getName();
                    categories.add(new Category(TempN));
                    catbuttons.get(id).setVisibility(View.VISIBLE);
                    catbuttons.get(id).setText(categories.get(id).getName());

                    int finalId = id;
                    catbuttons.get(id).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intentC = new Intent (MainActivity.this,CategoryView.class);
                            intentC.putExtra("co", categories.get(finalId));
                            startActivity(intentC);
                        }
                    });


                    for (int idf = 0; idf< database.get(id).getFlashcards().size() ; idf++) {
                        // loading flashcards from the database
                        TempF = database.get(id).getFlashcards().get(idf).getFront();
                        TempB = database.get(id).getFlashcards().get(idf).getBack();
                        categories.get(id).getFlashcards().add(new Flashcard(TempF, TempB) );
                    }
                }
        /********************************************************************************/

        /*****  MAKE A LIST OF BUTTONS TO (setNAME & setVISIBLITY) EACH TIME A CATEGORY IS CREATED  *****/

    }


    // magic button method
    public void launchMagicView (View V){
        Intent intentM = new Intent (this,MagicView.class);
        startActivity(intentM);
    }


}