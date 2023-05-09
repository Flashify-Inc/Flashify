package com.example.flashify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowAlertDialog;

import java.util.ArrayList;

public class JUnitTest {

    @Mock
    private MainActivity mockMainActivity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCategoryCreation() {
        Category category = new Category("Test Category");
        assertEquals("Test Category", category.getName());
    }

    @Test
    public void testFlashcardCreation() {
        Flashcard flashcard = new Flashcard("Test Front", "Test Back");
        assertEquals("Test Front", flashcard.getFront());
        assertEquals("Test Back", flashcard.getBack());
    }

    @Test
    public void testRenameButtonClickListener() {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().start().visible().get();
        LinearLayout outerLinearLayout = activity.findViewById(R.id.categoryLinearLayout);

        // Create a mock category object
        Category category = new Category("Category 1");
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(category);

        // Set the categories list in the activity
        activity.categories = categories;

        // Add a dynamic button to the outerLinearLayout
        LinearLayout innerLinearLayout = new LinearLayout(activity);
        innerLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        innerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        outerLinearLayout.addView(innerLinearLayout);

        Button button = new Button(activity);
        button.setText(category.getName());
        innerLinearLayout.addView(button);

        ImageButton renameBtn = new ImageButton(activity);
        innerLinearLayout.addView(renameBtn);

        // Perform a click on the renameBtn
        renameBtn.performClick();

        // Check that the rename dialog is displayed
        android.app.AlertDialog renameDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertNotNull(renameDialog);

        // Enter a new category name and click the Rename button
        EditText inputView = renameDialog.findViewById(android.R.id.edit);
        assertNotNull(inputView);

        inputView.setText("New Category Name");

        Button renameButton = renameDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        assertNotNull(renameButton);

        renameButton.performClick();

        // Check that the category name was updated
        assertEquals("New Category Name", category.getName());
    }



    @Test
    public void testButtonOnClickListener() {
        MainActivity activity = new MainActivity();
        activity.outerLinearLayout = new LinearLayout(activity);
        Category testCategory = new Category("Test Category");
        Flashcard testFlashcard = new Flashcard("Test front", "test back");
        testCategory.appendFlashcard(testFlashcard);
        activity.categories.add(testCategory);

        Button button = new Button(activity);
        ImageButton renameBtn = new ImageButton(activity);
        ImageButton deleteBtn = new ImageButton(activity);

        LinearLayout innerLinearLayout = new LinearLayout(activity);
        innerLinearLayout.addView(deleteBtn);
        innerLinearLayout.addView(button);
        innerLinearLayout.addView(renameBtn);
        activity.outerLinearLayout.addView(innerLinearLayout);

        button.performClick();
        Intent expectedIntent = new Intent(activity, CategoryViewActivity.class);
        expectedIntent.putExtra("categoryInd", 0);
        assertEquals(expectedIntent.getComponent(), activity.getIntent().getComponent());
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    private Object when(AlertDialog.Builder applicationContext) {
        return null;
    }

    @Test
    public void testDeleteBtnOnClickListener() {
        MainActivity activity = new MainActivity();
        activity.outerLinearLayout = new LinearLayout(activity);
        Category testCategory = new Category("Test Category");
        Flashcard testFlashcard = new Flashcard("Test front", "test back");
        testCategory.appendFlashcard(testFlashcard);
        activity.categories.add(testCategory);

        Button button = new Button(activity);
        ImageButton renameBtn = new ImageButton(activity);
        ImageButton deleteBtn = new ImageButton(activity);

        LinearLayout innerLinearLayout = new LinearLayout(activity);
        innerLinearLayout.setOrientation(LinearLayout.VERTICAL);
        innerLinearLayout.addView(renameBtn);
        innerLinearLayout.addView(deleteBtn);
        activity.outerLinearLayout.addView(innerLinearLayout);

        deleteBtn.performClick();
        assertTrue(activity.categories.isEmpty());
    }
}


