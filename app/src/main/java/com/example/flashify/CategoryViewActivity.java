package com.example.flashify;


import static com.example.flashify.MainActivity.categories;

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

    ImageButton editBtn;
    ImageButton deleteBtn;
    Button button;

    int categoryInd;

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

        categoryInd = getIntent().getIntExtra("categoryInd", 0);

        // localize the interactive buttons in the screen
        catText = findViewById(R.id.textCategoryView);
        catText.setText(categories.get(categoryInd).getName());

        edit = findViewById(R.id.editBtn2);

        /********* edit toggle ************/

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isEditOn = edit.isChecked();
                // toggle on
                    for (int innerLinearLayoutInd = 0; innerLinearLayoutInd < categories.get(categoryInd).getFlashcards().size(); innerLinearLayoutInd++) {
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

    private void refreshView() {
        outerLinearLayout.removeAllViews();

        for (int flashcardInd = 0; flashcardInd < categories.get(categoryInd).getFlashcards().size(); flashcardInd++) {
            // Create a new horizontal LinearLayout to hold the dynamic button and two smaller image buttons
            LinearLayout innerLinearLayout = new LinearLayout(this);
            innerLinearLayout.setLayoutParams(innerLayoutParams);
            innerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            innerLinearLayout.setGravity(Gravity.CENTER);


            // Create the dynamic button
            button = new Button(this);
            button.setText(categories.get(categoryInd).getFlashcards().get(flashcardInd).getFront());
            button.setBackgroundColor(0xFF6200ED);
            button.setLayoutParams(buttonParams);
            button.setTextColor(0xFFFFFFFF);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentF = new Intent(CategoryViewActivity.this, FlashcardViewActivity.class);
                    intentF.putExtra("categoryInd", categoryInd);
                    intentF.putExtra("flashcardInd", outerLinearLayout.indexOfChild(innerLinearLayout));
                    startActivity(intentF);
                }
            });

            // Create the two smaller image buttons
            editBtn = new ImageButton(this);
            editBtn.setImageResource(R.drawable.baseline_mode_edit_24);
            editBtn.setBackgroundColor(Color.TRANSPARENT);
            editBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);
            editBtn.setVisibility(View.INVISIBLE);
            editBtn.setLayoutParams(editButtonParams);

            int finalFlashcardInd = flashcardInd;
            editBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   editBtn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       // Create a dialog for editing the front text
                       AlertDialog.Builder builder1 = new AlertDialog.Builder(CategoryViewActivity.this);
                       builder1.setTitle("Edit Front Text");
                       final EditText frontEditText = new EditText(CategoryViewActivity.this);
                       frontEditText.setText(categories.get(categoryInd).getFlashcards().get(finalFlashcardInd).getFront());
                       builder1.setView(frontEditText);
                       builder1.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               String newFrontText = frontEditText.getText().toString();
                               categories.get(categoryInd).getFlashcards().get(finalFlashcardInd).setFront(newFrontText);
                               refreshView();
                           }
                       });
                       builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               // Do nothing
                           }
                       });
                       builder1.show();

                       // Create a dialog for editing the back text
                       AlertDialog.Builder builder2 = new AlertDialog.Builder(CategoryViewActivity.this);
                       builder2.setTitle("Edit Back Text");
                       final EditText backEditText = new EditText(CategoryViewActivity.this);
                       backEditText.setText(categories.get(categoryInd).getFlashcards().get(finalFlashcardInd).getBack());
                       builder2.setView(backEditText);
                       builder2.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               String newBackText = backEditText.getText().toString();
                               categories.get(categoryInd).getFlashcards().get(finalFlashcardInd).setBack(newBackText);
                               refreshView();
                           }
                       });
                       builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               // Do nothing
                           }
                       });
                       builder2.show();
                   }
               });
               }
            });

            deleteBtn = new ImageButton(this);
            deleteBtn.setImageResource(R.drawable.icons8_remove_96);
            deleteBtn.setBackgroundColor(Color.TRANSPARENT);
            deleteBtn.setVisibility(View.INVISIBLE);
            deleteBtn.setLayoutParams(deleteButtonParams);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categories.get(categoryInd).deleteFlashcard(outerLinearLayout.indexOfChild(innerLinearLayout));
                    refreshView();
                }
            });

            // Add the dynamic button and two smaller image buttons to the LinearLayout
            innerLinearLayout.addView(deleteBtn);
            innerLinearLayout.addView(button);
            innerLinearLayout.addView(editBtn);


            outerLinearLayout.addView(innerLinearLayout);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshView();
    }
}