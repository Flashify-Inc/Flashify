package com.example.flashify;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM Category")
    List<Category> getAllCategories();

    @Insert
    void insertCategory(Category category);

    @Query("DELETE FROM Category")
    void deleteAllCategories();



    //@Delete
    //void deleteCategory(Category category);

}
