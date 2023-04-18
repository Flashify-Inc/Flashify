package com.example.flashify;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FlashcardsDao {
    @Query("SELECT * FROM Flashcard")
    List<Flashcard> selectAll();

    //@Query("SELECT * FROM Flashcard WHERE categoryId=:id")
    //List<Flashcard> selectById(long id);

    @Insert
    void insertAll(Flashcard... flashcards);

   // @Update
   // int update(Flashcard flashcard);

    @Query("DELETE FROM Flashcard")
    void deleteAllFlashcards();

   // @Delete
   //int deleteAll(Flashcard... flashcards);
}
