package com.example.flashify;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CategoryViewActivity extends AppCompatActivity {
    TextView catText;
    Switch edit;

    LinearLayout.LayoutParams innerLayoutParams;
    LinearLayout outerLinearLayout;
    LinearLayout.LayoutParams buttonParams;
    LinearLayout.LayoutParams editButtonParams;
    LinearLayout.LayoutParams deleteButtonParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        outerLinearLayout = findViewById(R.id.flashcardLinearLayout);

        buttonParams = new LinearLayout.LayoutParams(0, convertDptoPx(90));
        buttonParams.weight = 4;

        editButtonParams = new LinearLayout.LayoutParams(0, convertDptoPx(60));
        editButtonParams.weight = 1;
        editButtonParams.leftMargin = convertDptoPx(10);

        deleteButtonParams = new LinearLayout.LayoutParams(0, convertDptoPx(60));
        deleteButtonParams.weight = 1;
        deleteButtonParams.rightMargin = convertDptoPx(10);

        innerLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        innerLayoutParams.setMargins(convertDptoPx(15), 0, convertDptoPx(15), convertDptoPx(20));

        Category category = (Category) getIntent().getParcelableExtra("category");

        // localize the interactive buttons in the screen
        catText = findViewById(R.id.textCategoryView);
        catText.setText(category.getName());

        edit = findViewById(R.id.editBtn2);

        for (Flashcard flashcard : category.getFlashcards()) {
            // Create a new horizontal LinearLayout to hold the dynamic button and two smaller image buttons
            LinearLayout innerLinearLayout = new LinearLayout(this);
            innerLinearLayout.setLayoutParams(innerLayoutParams);
            innerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            innerLinearLayout.setGravity(Gravity.CENTER);


            // Create the dynamic button
            Button button = new Button(this);
            button.setText(flashcard.getFront());
            button.setBackgroundColor(0xFF6200ED);
            button.setLayoutParams(buttonParams);
            button.setTextColor(0xFFFFFFFF);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentF = new Intent(CategoryViewActivity.this, FlashcardViewActivity.class);
                    intentF.putExtra("category", category);
                    intentF.putExtra("ind", outerLinearLayout.indexOfChild(innerLinearLayout));
                    startActivity(intentF);
                }
            });

            // Create the two smaller image buttons
            ImageButton editBtn = new ImageButton(this);
            editBtn.setImageResource(R.drawable.baseline_mode_edit_24);
            editBtn.setBackgroundColor(Color.TRANSPARENT);
            editBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);
            editBtn.setVisibility(View.INVISIBLE);
            editBtn.setLayoutParams(editButtonParams);

            editBtn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       //TODO
//                       // Create an EditText view to get user input
//                       final EditText inputView = new EditText(MainActivity.this);
//
//                       // Create a dialog with the EditText view as its content
//                       AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                       builder.setTitle("Enter the new category name: ")
//                               .setView(inputView)
//                               .setPositiveButton("Rename", new DialogInterface.OnClickListener() {
//                                   @Override
//                                   public void onClick(DialogInterface dialog, int which) {
//                                       // Get the text entered by the user
//                                       String inputText = inputView.getText().toString();
//
//                                       // Set the text of the button to the user input
//                                       button.setText(inputText);
//                                       categories.get(outerLinearLayout.indexOfChild(innerLinearLayout)).setName(inputText);
//                                   }
//                               })
//                               .setNegativeButton("Cancel", null)
//                               .show();

                   }
               }
            );

            ImageButton deleteBtn = new ImageButton(this);
            deleteBtn.setImageResource(R.drawable.icons8_remove_96);
            deleteBtn.setBackgroundColor(Color.TRANSPARENT);
            deleteBtn.setVisibility(View.INVISIBLE);
            deleteBtn.setLayoutParams(deleteButtonParams);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    category.deleteFlashcard(outerLinearLayout.indexOfChild(innerLinearLayout));
                    outerLinearLayout.removeView(innerLinearLayout);
                }
            });

            // Add the dynamic button and two smaller image buttons to the LinearLayout
            innerLinearLayout.addView(deleteBtn);
            innerLinearLayout.addView(button);
            innerLinearLayout.addView(editBtn);


            outerLinearLayout.addView(innerLinearLayout);
        }


        /********* edit toggle ************/

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isEditOn = edit.isChecked();
                // toggle on
                    for (int innerLinearLayoutInd = 0; innerLinearLayoutInd < category.getFlashcards().size(); innerLinearLayoutInd++) {
                        LinearLayout innerLinearLayout = (LinearLayout) outerLinearLayout.getChildAt(innerLinearLayoutInd);
                        if (isEditOn) {
                            innerLinearLayout.getChildAt(0).setVisibility(View.VISIBLE);
                            innerLinearLayout.getChildAt(2).setVisibility(View.VISIBLE);
                        } else {
                            innerLinearLayout.getChildAt(0).setVisibility(View.INVISIBLE);
                            innerLinearLayout.getChildAt(2).setVisibility(View.INVISIBLE);
                        }
                    }
            }
        });
    }

/********************STATIC APPROACH TO ADDING BUTTONS************************
        // Flashcard buttons
//        ArrayList<Button> FshButtons = new ArrayList<>();
//        fbtn1 = findViewById(R.id.BtnFlashcard1);
//        fbtn2 = findViewById(R.id.btnFlashcard2);
//        fbtn3 = findViewById(R.id.btnFlashcard3);
//        fbtn4 = findViewById(R.id.btnFlashcard4);
//        FshButtons.add(fbtn1);
//        FshButtons.add(fbtn2);
//        FshButtons.add(fbtn3);
//        FshButtons.add(fbtn4);

/************************* INITIALIZER *****************************

            // retrieve the category object from the previous activity
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
 */

///******************************************************************/
//
    private int convertDptoPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

/******************************************************************/

}