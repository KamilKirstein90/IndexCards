package com.kamilkirstein.indexcards.dao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kamilkirstein.indexcards.dto.IndexCardCategory;

import java.util.List;

@Dao
public interface IndexCardCategoryDao {

    @Insert
    void insertIndexCardCategory(IndexCardCategory category);

    //update a category
    @Update
    void updateIndexCardCategory(IndexCardCategory category);

    // query all categories
    @Query("SELECT * FROM indexcardcategory_table")
    LiveData<List<IndexCardCategory>> queryIndexCardCategories();

    // read one category
    @Query ("SELECT * FROM indexcardcategory_table WHERE id = :id ")
    LiveData<IndexCardCategory> readIndexCardCategory(@NonNull int id);

}
