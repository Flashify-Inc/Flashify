package com.example.flashify;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CategoryView extends AppCompatActivity {
    TextView catText;
    Button fbtn1, fbtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        catText = findViewById(R.id.textCategoryView);
        fbtn1 = findViewById(R.id.BtnFlashcard1);
        fbtn2 = findViewById(R.id.btnFlashcard2);


            // retrieve the category object from the previous activity
            Category c = (Category) getIntent().getParcelableExtra("co");
            catText.setText(c.getName());


            // first flashcard
            fbtn1.setText(c.getFlashcards().get(0).getFront());
            fbtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentF = new Intent (CategoryView.this, FlashcardView.class);
                    intentF.putExtra("co",c);
                    int index = 0;
                    intentF.putExtra("ind",index);
                    startActivity(intentF);
                }
            });

            // second flashcard
            fbtn2.setText(c.getFlashcards().get(1).getFront());
            fbtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    Intent intentF = new Intent (CategoryView.this, FlashcardView.class);
                    intentF.putExtra("co",c);
                    int index = 1;
                    intentF.putExtra("ind",index);
                    startActivity(intentF);
                }
            });

    }

}