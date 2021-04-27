package com.kamilkirstein.indexcards.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kamilkirstein.indexcards.dto.IndexCard;
import com.kamilkirstein.indexcards.dto.IndexCardCategory;

// Annotate the database and define all entities for it
@Database(entities =  {IndexCard.class, IndexCardCategory.class}, version = 1, exportSchema = false)
abstract public class IndexCardsDataBase extends RoomDatabase {

    private static IndexCardsDataBase INDEX_CARDS_DB_INSTANCE;

    public abstract IndexCardDao indexCardDao();
    public abstract IndexCardCategoryDao indexCardCategoryDao();

    // implement singleton pattern
    // synchronized we allow just one thread at any time to get access to the db instance
    public static synchronized IndexCardsDataBase getIndexCardsDB(final Context context){

        if (INDEX_CARDS_DB_INSTANCE == null)
        {
            synchronized (IndexCardsDataBase.class){
                if (INDEX_CARDS_DB_INSTANCE == null){
                    INDEX_CARDS_DB_INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            IndexCardsDataBase.class, "indexcards_database")
                            .build();
                }
            }
        }
        return INDEX_CARDS_DB_INSTANCE;
    }

}

