package com.example.flashify;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    static ArrayList<Category> categories;
    FloatingActionButton addBtn, manualBtn, magicBtn;
    Button categoryBtn1, categoryBtn2;
    Switch edit;
    ImageButton dlt1,dlt2,dlt3,dlt4,dlt5;
    ImageButton ec1,ec2,ec3,ec4,ec5;

    private AppDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // localize the interactive buttons in the screen
            addBtn=findViewById(R.id.openBtn);
            magicBtn=findViewById(R.id.magicBtn);
            manualBtn=findViewById(R.id.manualBtn);
            edit = findViewById(R.id.editbtn);
            dlt1 = findViewById(R.id.dlt1);
            dlt2 = findViewById(R.id.dlt2);
            dlt3 = findViewById(R.id.dlt3);
            dlt4 = findViewById(R.id.dlt4);
            dlt5 = findViewById(R.id.dlt5);
            ec1 = findViewById(R.id.editC1);
            ec2 = findViewById(R.id.editC2);
            ec3 = findViewById(R.id.editC3);
            ec4 = findViewById(R.id.editC4);
            ec5 = findViewById(R.id.editC5);

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

                db.flashcardsDao().deleteAllFlashcards();
                db.categoryDao().deleteAllCategories();

                CategoryDB mathCat = new CategoryDB("Mathematics");
                long mathCat_ID = db.categoryDao().insertCategory(mathCat);
                CategoryDB chemCat = new CategoryDB("Chemistry");
                long chemCat_ID = db.categoryDao().insertCategory(chemCat);
                CategoryDB bioCat = new CategoryDB("Biology");
                long bioCat_ID = db.categoryDao().insertCategory(bioCat);

                FlashcardDB fl1 = new FlashcardDB("2+2=", "4", mathCat_ID);
                FlashcardDB fl2 = new FlashcardDB("10+10=", "20", mathCat_ID);
                FlashcardDB fl3 = new FlashcardDB("hydrochloric acid formula", "HCl", chemCat_ID);
                FlashcardDB fl4 = new FlashcardDB("sulfuric acid formula", "H2SO4", chemCat_ID);
                FlashcardDB fl5 = new FlashcardDB("Mitosis", "Whatever Mitosis is", chemCat_ID);
                FlashcardDB fl6 = new FlashcardDB("chem shiz", ":(", chemCat_ID);
                FlashcardDB fl7 = new FlashcardDB("whats the chemical formula of your mom", "ObESe", chemCat_ID);
                db.flashcardsDao().insertFlashcard(fl1);
                db.flashcardsDao().insertFlashcard(fl2);
                db.flashcardsDao().insertFlashcard(fl3);
                db.flashcardsDao().insertFlashcard(fl4);
                db.flashcardsDao().insertFlashcard(fl5);
                db.flashcardsDao().insertFlashcard(fl6);
                db.flashcardsDao().insertFlashcard(fl7);



                // Getting everything from the database and storing it into CategoryDB and FlashcardsDB lists.
                List<CategoryDB> categoryDBList = db.categoryDao().getAllCategories();
                List<FlashcardDB> flashcardDBList = db.flashcardsDao().getAllFlashcards();
                //Log.d("DavidDebug", flashcardDBList.get(0).frontSide);

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

                // prepare the add category
                // catbuttons.get(id+1).setVisibility(View.VISIBLE);

                // activate the button
                catbuttons.get(id).setVisibility(View.VISIBLE);
                catbuttons.get(id).setText(categories.get(id).getName());
                catbuttons.get(id).setBackgroundColor(Color.parseColor("#6200ED"));

                int finalId = id;
                catbuttons.get(id).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intentC = new Intent (MainActivity.this,CategoryView.class);
                        intentC.putExtra("categoryNumber", categories.get(finalId));
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

        /********************************      EDIT TOGGLE     *************************/

        ArrayList<ImageButton> dlts = new ArrayList<ImageButton>();
        // delete buttons
        dlts.add(dlt1);
        dlts.add(dlt2);
        dlts.add(dlt3);
        dlts.add(dlt4);
        dlts.add(dlt5);

        //edit category buttons
        ArrayList<ImageButton> RenameCategories = new ArrayList<ImageButton>();
        RenameCategories.add(ec1);
        RenameCategories.add(ec2);
        RenameCategories.add(ec3);
        RenameCategories.add(ec4);
        RenameCategories.add(ec5);

        /********* edit toggle ************/
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isEditOn = edit.isChecked();
                // toggle on
                if (isEditOn) {
                    for (int dor=0; dor<categories.size(); dor++){
                        dlts.get(dor).setVisibility(View.VISIBLE);
                        RenameCategories.get(dor).setVisibility(View.VISIBLE);
                    }
                // toggle off
                } else {
                    for (int biha=0; biha<categories.size(); biha++){
                        dlts.get(biha).setVisibility(View.INVISIBLE);
                        RenameCategories.get(biha).setVisibility(View.INVISIBLE);
                    }

                }
            }
        });

        /********* delete a category ************/
        for (int biha = 0; biha < dlts.size(); biha++) {
            int finalBiha = biha;
            dlts.get(biha).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    categories.remove(finalBiha);

                    catbuttons.get(categories.size()).setVisibility(View.GONE);
                    dlts.get(categories.size()).setVisibility(View.GONE);
                    RenameCategories.get(categories.size()).setVisibility(View.GONE);


                    // from beginning
                    if( finalBiha == 0 )
                    {
                        for (int hna =0; hna < categories.size(); hna++ )
                            catbuttons.get(hna).setText(catbuttons.get(hna+1).getText());
                    }
                        // from the middle
                        else if( finalBiha != categories.size() )
                        {
                            for (int lhih = finalBiha; lhih < categories.size(); lhih++ )
                                catbuttons.get(lhih).setText(catbuttons.get(lhih+1).getText());
                        }

                        // neutralize the add button
                    catbuttons.get(categories.size()).setText("+ ADD CATEGORY ");
                    catbuttons.get(categories.size()).setBackgroundColor(Color.parseColor("#BA56DD"));

                        // to not leave the front empty
                    if( categories.size() == 0)
                        catbuttons.get(0).setVisibility(View.VISIBLE);


                }
            });
        }


        /********* rename a category  ************/
        for (int chibani = 0; chibani < categories.size(); chibani++) {
            int finalBiha = chibani;
            RenameCategories.get(chibani).setOnClickListener(new View.OnClickListener() {
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
                                    catbuttons.get(finalBiha).setText(inputText);
                                    categories.get(finalBiha).setName(inputText);
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                }
            });
        }

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

                                        categories.add(new Category( inputText ));
                                        catbuttons.get(categories.size()-1).setVisibility(View.VISIBLE);
                                        catbuttons.get(categories.size()-1).setText(inputText);
                                        catbuttons.get(categories.size()-1).setBackgroundColor(Color.parseColor("#6200ED"));

                                        Intent intent = new Intent(MainActivity.this, CategoryView.class);
                                        intent.putExtra("co", categories.get(categories.size()-1));
                                        startActivity(intent);
                                    }

                                })
                                .setNegativeButton("Cancel", null)
                                .show();
                    }
                });

    }

    // magic button method
    public void launchMagicView (View V){
        Intent intent = new Intent (this,MagicView.class);
        startActivity(intent);
    }


}