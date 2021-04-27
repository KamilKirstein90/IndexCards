package com.kamilkirstein.indexcards.dto;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "indexcardcategory_table")
public class IndexCardCategory {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name ="id")
    int id = 0;

    // TODO maybe this should be a primary Key as well
    @NonNull
    @ColumnInfo(name ="name")
    String name;

    List<IndexCard> idxCards = new ArrayList<IndexCard>();

    public IndexCardCategory() {
        clearData();
    }

    public void clearData(){
        id = 0;
        name = "";
        idxCards.clear();
    }

    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public List<IndexCard> getIdxCards() {
        return idxCards;
    }

    public void setIdxCards(List<IndexCard> idxCards) {
        this.idxCards = idxCards;
    }

     public void appendCard(IndexCard card){
        idxCards.add(card);
     }
}
