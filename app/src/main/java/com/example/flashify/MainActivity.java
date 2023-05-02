package com.example.flashify;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    FloatingActionButton addBtn, manualBtn, magicBtn;
    Button categoryBtn1, categoryBtn2;

    private AppDatabase db;

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

        // CONNECTING THE DATABASE
        db = AppDatabase.getInstance(getApplicationContext());


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
*/
                // Getting everything from the database and storing it into CategoryDB and FlashcardsDB lists.
                List<CategoryDB> categoryDBList = db.categoryDao().getAllCategories();
                List<FlashcardDB> flashcardDBList = db.flashcardsDao().getAllFlashcards();
                //Log.d("DavidDebug", flashcardDBList.get(0).frontSide);

                // Category initialization
                ArrayList<Category> categories = new ArrayList<Category>();

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


            for (int idf = 0; idf < flashcardDBList.size(); idf++) {
                // loading flashcards from the database
                if (flashcardDBList.get(idf).categoryId == TempCatId) {
                    TempF = flashcardDBList.get(idf).frontSide;
                    TempB = flashcardDBList.get(idf).backSide;
                    categories.get(id).getFlashcards().add(new Flashcard(TempF, TempB));
                }

            }
        }

                //Erase the database entirely
                db.flashcardsDao().deleteAllFlashcards();
                db.categoryDao().deleteAllCategories();


                // Creating new CategoryDBs and FlashcardDBs from Category and Flashcard ArrayLists

                /*
                Category newCategory = new Category("");
                Flashcard newFlashcard = new Flashcard("Mitosis", "whatever Mitosis is");
                newCategory.getFlashcards().add(newFlashcard);
                categories.add(newCategory);
                */

                // UPDATING THE DATABASE
                for (int i = 0; i < categories.size(); i++){
                    CategoryDB curCategoryDB = new CategoryDB(categories.get(i).getName());
                    long curCategoryDB_ID = db.categoryDao().insertCategory(curCategoryDB);
                    for (int j = 0; j < categories.get(i).getFlashcards().size(); j++){
                        FlashcardDB curFlashcardDB =  new FlashcardDB(categories.get(i).getFlashcards().get(j).getFront(),
                                categories.get(i).getFlashcards().get(j).getBack(), curCategoryDB_ID);
                        db.flashcardsDao().insertFlashcard(curFlashcardDB);
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