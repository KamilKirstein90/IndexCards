package com.kamilkirstein.indexcards.dao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kamilkirstein.indexcards.dto.IndexCard;
import com.kamilkirstein.indexcards.dto.IndexCardCategory;

import java.util.List;

@Dao
public interface IndexCardDao {

    @Insert
    void insertIndexCard(IndexCard card);

    @Update
    void updateIndexCard(IndexCard card);

    @Query ("SELECT * FROM indexcard_table")
    LiveData<List<IndexCard>> queryIndexCards();

    // read one index card
    @Query ("SELECT * FROM indexcard_table WHERE id = :id")
    LiveData<IndexCard> readIndexCard(@NonNull int id);

}
