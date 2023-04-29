package com.example.flashify;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity (foreignKeys = @ForeignKey(entity = CategoryDB.class, parentColumns = "id",
        childColumns = "category_id") )
public class FlashcardDB {
    @PrimaryKey(autoGenerate = true)
    public long flashcardId;

    @ColumnInfo(name = "front_side")
    public String frontSide;

    @ColumnInfo(name = "back_side")
    public String backSide;

    @ColumnInfo(name = "category_id")
    public long categoryId;


    //constructor
    public FlashcardDB(long flashcardId, String frontSide, String backSide, long categoryId){
        this.flashcardId = flashcardId;
        this.frontSide = frontSide;
        this.backSide = backSide;
        this.categoryId = categoryId;

    }

    @Ignore
    public FlashcardDB(String frontSide, String backSide, long categoryId) {
        this.frontSide = frontSide;
        this.backSide = backSide;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return frontSide;
    }

}
