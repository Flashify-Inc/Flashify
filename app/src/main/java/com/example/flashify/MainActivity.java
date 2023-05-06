package com.example.flashify;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    static ArrayList<Category> categories;
    FloatingActionButton addBtn, manualBtn, magicBtn;
    Switch edit;

    LinearLayout.LayoutParams innerLayoutParams;
    LinearLayout outerLinearLayout;
    LinearLayout.LayoutParams buttonParams;
    LinearLayout.LayoutParams editButtonParams;
    LinearLayout.LayoutParams deleteButtonParams;


    private AppDatabase db;

    private void loadCategoriesFromDB() {
        db = AppDatabase.getInstance(getApplicationContext());

        // Getting everything from the database and storing it into CategoryDB and FlashcardsDB lists.
        List<CategoryDB> categoryDBList = db.categoryDao().getAllCategories();
        List<FlashcardDB> flashcardDBList = db.flashcardsDao().getAllFlashcards();

        // Category initialization
        categories = new ArrayList<Category>();

        //Taking everything from categoryDBList and flashcardDBList and putting them in local categories list
        String TempN, TempF = "", TempB = "";
        long TempCatId;


        for(int id = 0; id< categoryDBList.size() ; id++) {
            // loading category from the database
            TempN = categoryDBList.get(id).categoryName;
            TempCatId = categoryDBList.get(id).id;

            Category curCategory = new Category(TempN);
            curCategory.setCategoryId(TempCatId);

            categories.add(curCategory);

            for (int idf = 0; idf < flashcardDBList.size(); idf++) {
                // loading flashcards from the database
                if (flashcardDBList.get(idf).category_id == TempCatId) {
                    TempF = flashcardDBList.get(idf).front_side;
                    TempB = flashcardDBList.get(idf).back_side;
                    categories.get(id).getFlashcards().add(new Flashcard(TempF, TempB));
                }

            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadCategoriesFromDB();

        // localize the interactive buttons in the screen
        addBtn=findViewById(R.id.openBtn);
        magicBtn=findViewById(R.id.magicBtn);
        manualBtn=findViewById(R.id.manualBtn);
        edit = findViewById(R.id.editbtn);

        outerLinearLayout = findViewById(R.id.categoryLinearLayout);

        buttonParams = new LinearLayout.LayoutParams(0, convertDptoPx(70));
        buttonParams.weight = 4;

        editButtonParams = new LinearLayout.LayoutParams(0, convertDptoPx(60));
        editButtonParams.weight = 1;
        editButtonParams.leftMargin = convertDptoPx(10);

        deleteButtonParams = new LinearLayout.LayoutParams(0, convertDptoPx(60));
        deleteButtonParams.weight = 1;
        deleteButtonParams.rightMargin = convertDptoPx(10);

        innerLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        innerLayoutParams.setMargins(convertDptoPx(15), 0, convertDptoPx(15), convertDptoPx(20));

        refreshView();

        /********* edit toggle ************/
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isEditOn = edit.isChecked();
                // toggle on
                for (int innerLinearLayoutInd = 0; innerLinearLayoutInd < categories.size(); innerLinearLayoutInd++) {
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

        // add a category
        manualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        // Create an EditText view to get user input
                        final EditText inputView = new EditText(MainActivity.this);

                        // Create a dialog with the EditText view as its content
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        builder.setTitle("New category name: ")
                                .setView(inputView)
                                .setPositiveButton("create", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Get the text entered by the user
                                        String inputText = inputView.getText().toString();

                                        // Set the text of the button to the user input

                                        Category newCategory = new Category( inputText );
                                        categories.add(newCategory);

                                        Intent intent = new Intent(MainActivity.this, CategoryViewActivity.class);
                                        intent.putExtra("category", newCategory);
                                        startActivity(intent);
                                    }

                                })
                                .setNegativeButton("Cancel", null)
                                .show();
                    }
                });

        // CONNECTING THE DATABASE


        /***************************   INITIALIZATION   ******************************/

                // YOUNESS' DUMMY DATA
                /*
                ArrayList<Category> database = new ArrayList<Category>();
                database.add(new Category("Geography" ));
                database.add(new Category("Chemistry" ));;
                database.add(new Category("Supreme lord emperor" ));
                database.get(0).getFlashcards().add(new Flashcard("what is the capital of Spain ?", "Madrid"));
                database.get(0).getFlashcards().add(new Flashcard ("What is the capital of Italy ?", "Rome"));
                //database.get(1).getFlashcards().add(new Flashcard ("H2O","the water chemical compound"));
                database.get(1).getFlashcards().add(new Flashcard ("dilution","the act of decreasing the concentration of a soluble"));
//*/
//
//
//
//
//        /********************************************************************************/


    }

    // magic button method
    public void launchMagicView (View V){
        Intent intent = new Intent (this, MagicViewActivity.class);
        startActivity(intent);
    }

    private int convertDptoPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    private void refreshView() {
        outerLinearLayout.removeAllViews();
        for (int categoryInd = 0; categoryInd < categories.size(); categoryInd++) {
            // Create a new horizontal LinearLayout to hold the dynamic button and two smaller image buttons
            LinearLayout innerLinearLayout = new LinearLayout(this);
            innerLinearLayout.setLayoutParams(innerLayoutParams);
            innerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            innerLinearLayout.setGravity(Gravity.CENTER);

            // Create the dynamic button
            Button button = new Button(this);
            button.setText(categories.get(categoryInd).getName());
            button.setBackgroundColor(0xFF6200ED);
            button.setLayoutParams(buttonParams);
            button.setTextColor(0xFFFFFFFF);

            int finalCategoryInd = categoryInd;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentF = new Intent(MainActivity.this, CategoryViewActivity.class);
                    intentF.putExtra("categoryInd", finalCategoryInd);
                    startActivity(intentF);
                }
            });

            // Create the two smaller image buttons
            ImageButton renameBtn = new ImageButton(this);
            renameBtn.setImageResource(R.drawable.baseline_mode_edit_24);
            renameBtn.setBackgroundColor(Color.TRANSPARENT);
            renameBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);
            renameBtn.setVisibility(View.INVISIBLE);
            renameBtn.setLayoutParams(editButtonParams);

            renameBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an EditText view to get user input
                    final EditText inputView = new EditText(MainActivity.this);

                    // Create a dialog with the EditText view as its content
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Enter the new category name: ")
                            .setView(inputView)
                            .setPositiveButton("Rename", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Get the text entered by the user
                                    String inputText = inputView.getText().toString();

                                    // Set the text of the button to the user input
                                    button.setText(inputText);
                                    categories.get(outerLinearLayout.indexOfChild(innerLinearLayout)).setName(inputText);
                                    Category curCategory = new Category(inputText);
                                    categories.add(curCategory);
                                    Log.d("DavidDebug", String.valueOf(categories.size()));
                                    //CategoryDB curCategoryDB = new CategoryDB(curCategory.getName());
                                    //long curCategoryId = db.categoryDao().insertCategory(Categor)

                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();


                }
            });

            ImageButton deleteBtn = new ImageButton(this);
            deleteBtn.setImageResource(R.drawable.icons8_remove_96);
            deleteBtn.setBackgroundColor(Color.TRANSPARENT);
            deleteBtn.setVisibility(View.INVISIBLE);
            deleteBtn.setLayoutParams(deleteButtonParams);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    categories.remove(outerLinearLayout.indexOfChild(innerLinearLayout));
                    outerLinearLayout.removeView(innerLinearLayout);
                }
            });

            // Add the dynamic button and two smaller image buttons to the LinearLayout
            innerLinearLayout.addView(deleteBtn);
            innerLinearLayout.addView(button);
            innerLinearLayout.addView(renameBtn);

            outerLinearLayout.addView(innerLinearLayout);
        }
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Erase the database entirely
        db.flashcardsDao().deleteAllFlashcards();
        db.categoryDao().deleteAllCategories();

        // UPDATING THE DATABASE
        for (int i = 0; i < categories.size(); i++){
            CategoryDB curCategoryDB = new CategoryDB(categories.get(i).getName());
            long curCategoryDB_ID = db.categoryDao().insertCategory(curCategoryDB);
            if (categories.get(i).getFlashcards().size() != 0){
                for (int j = 0; j < categories.get(i).getFlashcards().size(); j++){
                    FlashcardDB curFlashcardDB =  new FlashcardDB(categories.get(i).getFlashcards().get(j).getFront(),
                            categories.get(i).getFlashcards().get(j).getBack(), curCategoryDB_ID);
                    db.flashcardsDao().insertFlashcard(curFlashcardDB);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshView();
    }
}