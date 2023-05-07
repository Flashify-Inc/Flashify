package com.example.flashify;

import static com.example.flashify.MainActivity.categories;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FlashcardViewActivity extends AppCompatActivity {
    ToggleButton FlashC;
    int flashcardInd;

    Switch toggleEdit;
    int categoryInd;
    ImageButton nextf, prevf, deleteButton, editButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_view);

        // localize the interactive buttons in the screen
        FlashC = findViewById(R.id.FlachCD);
        nextf = findViewById(R.id.nextF);
        prevf = findViewById(R.id.prevF);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
        toggleEdit = findViewById(R.id.toggleEdit);

        // retrieve the data
        categoryInd = getIntent().getIntExtra("categoryInd", 0);
        Bundle extras = getIntent().getExtras();
        flashcardInd = extras.getInt("flashcardInd");

        deleteButton.setVisibility(View.INVISIBLE);
        editButton.setVisibility(View.INVISIBLE);
        // view the flashcard
        refreshView();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categories.get(categoryInd).deleteFlashcard(flashcardInd);
                if (flashcardInd == categories.get(categoryInd).getFlashcards().size()) {
                    flashcardInd = flashcardInd-1;
                } ;
                refreshView();
            }
        });

        // next button
        nextf.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (FlashC.isChecked())
                        FlashC.toggle();

                    prevf.setVisibility(View.VISIBLE);

                    flashcardInd = flashcardInd+1;
                    refreshView();
                }

        });

        // previous button
        prevf.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FlashC.isChecked())
                    FlashC.toggle();

                nextf.setVisibility(View.VISIBLE);

                flashcardInd = flashcardInd-1;
                refreshView();
            }
        });

        toggleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isEditOn = toggleEdit.isChecked();
                // toggle on
                    if (isEditOn) {
                        deleteButton.setVisibility(View.VISIBLE);
                        editButton.setVisibility(View.VISIBLE);
                    } else {
                        deleteButton.setVisibility(View.INVISIBLE);
                        editButton.setVisibility(View.INVISIBLE);
                    }
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a dialog for editing the front text
                AlertDialog.Builder builder1 = new AlertDialog.Builder(FlashcardViewActivity.this);
                builder1.setTitle("Edit Front Text");
                final EditText frontEditText = new EditText(FlashcardViewActivity.this);
                frontEditText.setText(categories.get(categoryInd).getFlashcards().get(flashcardInd).getFront());
                builder1.setView(frontEditText);
                builder1.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newFrontText = frontEditText.getText().toString();
                        categories.get(categoryInd).getFlashcards().get(flashcardInd).setFront(newFrontText);
                        refreshView();
                    }
                });
                builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do nothing
                    }
                });

                // Create a dialog for editing the back text
                AlertDialog.Builder builder2 = new AlertDialog.Builder(FlashcardViewActivity.this);
                builder2.setTitle("Edit Back Text");
                final EditText backEditText = new EditText(FlashcardViewActivity.this);
                backEditText.setText(categories.get(categoryInd).getFlashcards().get(flashcardInd).getBack());
                builder2.setView(backEditText);
                builder2.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newBackText = backEditText.getText().toString();
                        categories.get(categoryInd).getFlashcards().get(flashcardInd).setBack(newBackText);
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
                builder1.show();
            }
        });

    }

    public void launchMainActivity (View V){
        Intent intent = new Intent (this,MainActivity.class);
        startActivity(intent);
    }

    private void refreshView() {
        if( flashcardInd == 0 ) {
            prevf.setVisibility(View.INVISIBLE);
        } else if (flashcardInd+1 > categories.get(categoryInd).getFlashcards().size() - 1 ){
            {nextf.setVisibility(View.INVISIBLE);}
        }

        FlashC.setText(categories.get(categoryInd).getFlashcards().get(flashcardInd).getFront());
        FlashC.setTextOff(categories.get(categoryInd).getFlashcards().get(flashcardInd).getFront());
        FlashC.setTextOn(categories.get(categoryInd).getFlashcards().get(flashcardInd).getBack());

    }

}