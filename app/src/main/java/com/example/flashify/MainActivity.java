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
    Button categoryBtn1, categoryBtn2;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn=findViewById(R.id.openBtn);
        magicBtn=findViewById(R.id.magicBtn);
        manualBtn=findViewById(R.id.manualBtn);

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

        /***************************     INITIALIZATION     ******************************/

                // DATABASE DUMMY DATA
                ArrayList<Category> database = new ArrayList<Category>();
                database.add(new Category("Geography" ));
                database.add(new Category("Chemistry" ));
                database.get(0).getFlashcards().add(new Flashcard("what is the capital of Spain ?", "Madrid"));
                database.get(0).getFlashcards().add(new Flashcard ("What is the capital of Italy ?", "Rome"));
                database.get(1).getFlashcards().add(new Flashcard ("H2O","the water chemical compound"));
                database.get(1).getFlashcards().add(new Flashcard ("dilution","the act of decreasing the concentration of a soluble"));


                // Category initialization
                ArrayList<Category> categories = new ArrayList<Category>();
                String TempN, TempF, TempB;

                for(int id = 0; id< database.size() ; id++) {
                    // loading category from the database
                    TempN = database.get(id).getName();
                    categories.add(new Category(TempN));

                    for (int idf = 0; idf< database.get(id).getFlashcards().size() ; idf++) {
                        // loading flashcards from the database
                        TempF = database.get(id).getFlashcards().get(idf).getFront();
                        TempB = database.get(id).getFlashcards().get(idf).getBack();
                        categories.get(id).getFlashcards().add(new Flashcard(TempF, TempB) );
                    }
                }

        /*****  MAKE A LIST OF BUTTONS TO (setNAME & setVISIBLITY) EACH TIME A CATEGORY IS CREATED  *****/
        categoryBtn1 = findViewById(R.id.btnCategory1);
        categoryBtn2 = findViewById(R.id.btnCategory2);

        categoryBtn1.setText(categories.get(0).getName());
        categoryBtn2.setText(categories.get(1).getName());

        //first category
        categoryBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentC = new Intent (MainActivity.this,CategoryView.class);
                intentC.putExtra("co", categories.get(0));
                startActivity(intentC);
            }
        });

        //second category
        categoryBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentC = new Intent (MainActivity.this,CategoryView.class);
                intentC.putExtra("co", categories.get(1));
                startActivity(intentC);
            }
        });


    }


    // magic button
    public void launchMagicView (View V){
        Intent intentM = new Intent (this,MagicView.class);
        startActivity(intentM);
    }



}