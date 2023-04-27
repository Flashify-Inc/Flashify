package com.example.flashify;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import java.util.LinkedList;


public class Category implements Parcelable {
    private String name;
    private LinkedList<Flashcard> Flashcards;

    public Category(String name) {
        this.name = name;
        this.Flashcards = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Flashcard> getFlashcards() {
        return Flashcards;
    }

    public void setFlashcards(LinkedList<Flashcard> flashcards) {
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
        Flashcards = new LinkedList<>();
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
