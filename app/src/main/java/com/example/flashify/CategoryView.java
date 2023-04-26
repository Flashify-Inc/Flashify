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
    int flag1, flag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);


        catText = findViewById(R.id.textCategoryView);
        fbtn1 = findViewById(R.id.BtnFlashcard1);
        fbtn2 = findViewById(R.id.btnFlashcard2);

        Bundle extras = getIntent().getExtras();
        flag1 = extras.getInt("flag1");

        if( flag1 == 1 ){

            Category c1 = (Category) getIntent().getParcelableExtra("co1");
            catText.setText(c1.getName());

            fbtn1.setText(c1.getFlashcards().get(0).getFront());
            fbtn2.setText(c1.getFlashcards().get(1).getFront());

            fbtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentF = new Intent (CategoryView.this, FlashcardView.class);
                    intentF.putExtra("co",c1);
                    flag2 = 1;
                    intentF.putExtra("flag2",flag2);
                    startActivity(intentF);
                }
            });

            fbtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    Intent intentF = new Intent (CategoryView.this, FlashcardView.class);
                    intentF.putExtra("co",c1);
                    flag2 = 2;
                    intentF.putExtra("flag2",flag2);
                    startActivity(intentF);
                }
            });

        }

        if(flag1 == 2 ){

            Category c2 = (Category) getIntent().getParcelableExtra("co2");
            catText.setText(c2.getName());

            fbtn1.setText(c2.getFlashcards().get(0).getFront());
            fbtn2.setText(c2.getFlashcards().get(1).getFront());

            fbtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view3) {
                    Intent intentF = new Intent (CategoryView.this, FlashcardView.class);
                    intentF.putExtra("co",c2);
                    flag2 = 1;
                    intentF.putExtra("flag2",flag2);
                    startActivity(intentF);
                }
            });

            fbtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view4) {
                    Intent intentF = new Intent (CategoryView.this, FlashcardView.class);
                    intentF.putExtra("co",c2);
                    flag2 = 2;
                    intentF.putExtra("flag2",flag2);
                    startActivity(intentF);
                }
            });
        }


    }

}