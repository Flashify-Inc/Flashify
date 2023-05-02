package com.example.flashify;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM CategoryDB")
    List<CategoryDB> getAllCategories();

    @Insert
    long insertCategory(CategoryDB categoryDB);

    @Query("DELETE FROM CategoryDB")
    void deleteAllCategories();

}
