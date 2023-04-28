package com.example.flashify;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class CategoryDB {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "category_name")
    public String categoryName;

    public CategoryDB(long id, String categoryName){
        this.id = id;
        this.categoryName = categoryName;
    }

    public long getCategoryId (){
        return id;
    }


    @Ignore
    public CategoryDB(String categoryName) {
        this.categoryName = categoryName;
    }


}
