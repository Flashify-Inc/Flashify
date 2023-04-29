package com.example.flashify;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FlashcardsDao {

    @Query("SELECT * FROM FlashcardDB")
    List<FlashcardDB> getAllFlashcards();

    //@Query("SELECT * FROM Flashcard WHERE categoryId=:id")
    //List<Flashcard> selectById(long id);

    @Insert
    void insertAll(FlashcardDB... flashcardDBS);

   // @Update
   // int update(Flashcard flashcard);

    @Query("DELETE FROM FlashcardDB")
    void deleteAllFlashcards();

    // @Delete
   //int deleteAll(Flashcard... flashcards);
}
