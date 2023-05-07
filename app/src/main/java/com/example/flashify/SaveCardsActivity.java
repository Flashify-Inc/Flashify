package com.example.flashify;

import static com.example.flashify.MagicTextViewActivity.generatedFlashcards;
import static com.example.flashify.MainActivity.categories;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SaveCardsActivity extends AppCompatActivity {

    ScrollView.LayoutParams outerLayoutParams;
    LinearLayout outerLinearLayout;
    LinearLayout.LayoutParams buttonParams;

    Button newCategoryButton;

    boolean newCategorySelected;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_cards);

        outerLinearLayout = findViewById(R.id.saveCardsLinearLayout);

        buttonParams = new LinearLayout.LayoutParams(convertDptoPx(300), convertDptoPx(70));

        outerLayoutParams = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(convertDptoPx(15), 0, convertDptoPx(15), convertDptoPx(20));
        // Add a click listener to the save button
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedCategory == -1) {
                    // Show an error message if the user has not selected a category
                    AlertDialog.Builder builder = new AlertDialog.Builder(SaveCardsActivity.this);
                    builder.setMessage("Please select a category first")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create().show();
                } else if (selectedCategory == -2) {
                    showNewCategoryDialog();

                } else {
                    categories.get(selectedCategory).getFlashcards().addAll(generatedFlashcards);
                    Intent intent = new Intent (SaveCardsActivity.this,CategoryViewActivity.class);
                    intent.putExtra("categoryInd", selectedCategory);
                    startActivity(intent);
                    // Save the flashcards to the selected category and navigate back to the main activity
                    // ...
                }
            }
        });
        refreshView();
    }

    private void showNewCategoryDialog() {
        final EditText editText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create New Category")
                .setView(editText)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String categoryName = editText.getText().toString();
                        Category newCategory = new Category(categoryName);
                        categories.add(newCategory);
                        refreshView();
                        selectedCategory = categories.size() - 1;
                        categories.get(selectedCategory).getFlashcards().addAll(generatedFlashcards);
                        Intent intent = new Intent (SaveCardsActivity.this,CategoryViewActivity.class);
                        intent.putExtra("categoryInd", selectedCategory);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }
    private int convertDptoPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    private int selectedCategory = -1;

    private void refreshView() {

        outerLinearLayout.removeAllViews();
        outerLinearLayout.setLayoutParams(outerLayoutParams);
        outerLinearLayout.setOrientation(LinearLayout.VERTICAL);
        outerLinearLayout.setGravity(Gravity.CENTER);

        for (int categoryInd = 0; categoryInd < categories.size(); categoryInd++) {
            // Create the dynamic button
            Button button = new Button(this);
            button.setText(categories.get(categoryInd).getName());
            button.setBackgroundColor(selectedCategory == categoryInd ? Color.BLACK : 0xFF6200ED);
            button.setLayoutParams(buttonParams);
            button.setTextColor(0xFFFFFFFF);

            int finalCategoryInd = categoryInd;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedCategory = finalCategoryInd;
                    refreshView();
                }
            });
            outerLinearLayout.addView(button);
        }

        newCategoryButton = new Button(this);
        newCategoryButton.setText("+ New Category");
        newCategoryButton.setBackgroundColor(selectedCategory == -2 ? Color.BLACK : 0xff6432a8);
        newCategoryButton.setLayoutParams(buttonParams);
        newCategoryButton.setTextColor(0xFFFFFFFF);
        newCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCategory = -2;
                refreshView();
            }
        });
        outerLinearLayout.addView(newCategoryButton);

    }

}