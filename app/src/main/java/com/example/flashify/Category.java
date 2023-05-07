package com.example.flashify;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class Category implements Parcelable {
    private long categoryId;
    private String name;
    private ArrayList<Flashcard> Flashcards;

    public Category(String name) {
        this.name = name;
        this.Flashcards = new ArrayList<>();
    }

    public long getCategoryId(){return categoryId;}

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void deleteFlashcard(int ind) {
        Flashcards.remove(ind);
    }

    public void appendFlashcard(Flashcard flashcard) {Flashcards.add(flashcard);}

    public ArrayList<Flashcard> getFlashcards() {
        return Flashcards;
    }

    public Flashcard getFlashcard(int ind) {
        return getFlashcards().get(ind);
    }

    public void setFlashcards(ArrayList<Flashcard> flashcards) {
        Flashcards = flashcards;
    }


    /********************** PARCELABLE STUFF ****************************/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeTypedList(Flashcards);
    }


    protected Category(Parcel in) {
        name = in.readString();
        Flashcards = new ArrayList<>();
        in.readTypedList(Flashcards, Flashcard.CREATOR);
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };


}
