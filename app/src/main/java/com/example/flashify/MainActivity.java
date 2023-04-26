package com.example.flashify;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton addBtn, manualBtn, magicBtn;
    Button categoryBtn1, categoryBtn2;
    int flag1;


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


        Flashcard f1 = new Flashcard("what is the capital of Spain ?", "Madrid");
        Flashcard f2 = new Flashcard ("What is the capital of Italy ?", "Rome");
        Flashcard f3 = new Flashcard ("H2O","the water chemical compound");
        Flashcard f4 = new Flashcard ("dilution","the act of decreasing the concentration of a soluble");

        Category c1 = new Category("Geography" );
        c1.getFlashcards().add(f1);
        c1.getFlashcards().add(f2);

        Category c2 = new Category("Chemistry");
        c2.getFlashcards().add(f3);
        c2.getFlashcards().add(f4);

        categoryBtn1 = findViewById(R.id.btnCategory1);
        categoryBtn2 = findViewById(R.id.btnCategory2);


        categoryBtn1.setText(c1.getName());
        categoryBtn2.setText(c2.getName());

        categoryBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentC = new Intent (MainActivity.this,CategoryView.class);
                intentC.putExtra("co1", c1);
                flag1 = 1;
                intentC.putExtra("flag1", flag1);
                startActivity(intentC);
            }
        });

        categoryBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentC = new Intent (MainActivity.this,CategoryView.class);
                intentC.putExtra("co2", c2);
                flag1 = 2;
                intentC.putExtra("flag1", flag1);
                startActivity(intentC);
            }
        });


    }


    public void launchMagicView (View V){
        Intent intentM = new Intent (this,MagicView.class);
        startActivity(intentM);
    }



}