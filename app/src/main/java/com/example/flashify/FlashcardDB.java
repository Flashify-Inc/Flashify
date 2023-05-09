package com.example.flashify;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity (foreignKeys = @ForeignKey(entity = CategoryDB.class, parentColumns = "id",
        childColumns = "category_id", onDelete = ForeignKey.CASCADE ))
public class FlashcardDB {
    @PrimaryKey(autoGenerate = true)
    public long flashcardId;

    @ColumnInfo(name = "front_side")
    public String front_side;

    @ColumnInfo(name = "back_side")
    public String back_side;

    @ColumnInfo(name = "category_id")
    public long category_id;


    //constructor
    public FlashcardDB(long flashcardId, String front_side, String back_side, long category_id){
        this.flashcardId = flashcardId;
        this.front_side = front_side;
        this.back_side = back_side;
        this.category_id = category_id;

    }

    @Ignore
    public FlashcardDB(String front_side, String back_side, long category_id) {
        this.front_side = front_side;
        this.back_side = back_side;
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return front_side;
    }

}
