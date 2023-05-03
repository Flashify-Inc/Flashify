package com.example.flashify;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CategoryView extends AppCompatActivity {
    TextView catText;
    int i;

    Button fbtn1, fbtn2, fbtn3, fbtn4 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        LinearLayout buttonLayout = findViewById(R.id.categoryLinearLayout);
        Category c = (Category) getIntent().getParcelableExtra("categoryNumber");


        for (i = 0; i < c.getFlashcards().size(); i++) {
            Button button = new Button(this);
            button.setText(c.getFlashcard(i).getFront());
            button.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Intent intentF = new Intent (CategoryView.this, FlashcardView.class);
                      intentF.putExtra("categoryNumber",c);
                      intentF.putExtra("ind",i);
                      startActivity(intentF);
                  }
            });
            buttonLayout.addView(button);
        }

        // localize the interactive buttons in the screen
        catText = findViewById(R.id.textCategoryView);
        catText.setText(c.getName());
        // Flashcard buttons
        ArrayList<Button> FshButtons = new ArrayList<>();
//        fbtn1 = findViewById(R.id.BtnFlashcard1);
//        fbtn2 = findViewById(R.id.btnFlashcard2);
//        fbtn3 = findViewById(R.id.btnFlashcard3);
//        fbtn4 = findViewById(R.id.btnFlashcard4);
//        FshButtons.add(fbtn1);
//        FshButtons.add(fbtn2);
//        FshButtons.add(fbtn3);
//        FshButtons.add(fbtn4);

/************************* INITIALIZER *****************************/
//
//            // retrieve the category object from the previous activity
//            catText.setText(c.getName());
//
//            // initialize the fsh buttons
//            for (int id = 0; id < c.getFlashcards().size() ; id++){
//                FshButtons.get(id).setText(c.getFlashcards().get(id).getFront());
//                FshButtons.get(id).setVisibility(View.VISIBLE);
//                int finalId = id;
//                FshButtons.get(id).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intentF = new Intent (CategoryView.this, FlashcardView.class);
//                        intentF.putExtra("co",c);
//                        int index = finalId;
//                        intentF.putExtra("ind",index);
//                        startActivity(intentF);
//                    }
//                });
//
//            }
//
//
///******************************************************************/
//
    }

}