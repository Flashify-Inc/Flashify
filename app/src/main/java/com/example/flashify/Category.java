package com.example.flashify;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "category_name")
    public String categoryName;

    public Category(long id, String categoryName){
        this.id = id;
        this.categoryName = categoryName;
    }


    @Ignore
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }



}
