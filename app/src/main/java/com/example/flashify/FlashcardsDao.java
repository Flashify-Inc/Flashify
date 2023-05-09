package com.example.flashify;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FlashcardsDao {

    @Query("SELECT * FROM FlashcardDB")
    List<FlashcardDB> getAllFlashcards();

    @Insert
    void insertFlashcard(FlashcardDB flashcardDB);

    @Query("DELETE FROM FlashcardDB")
    void deleteAllFlashcards();

    @Query("SELECT * FROM FlashcardDB WHERE category_id = :category_id")
    List<FlashcardDB> getAllFlashcardsOfCategory(long category_id);
}
