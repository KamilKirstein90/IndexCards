package com.kamilkirstein.indexcards.dto;

import java.util.ArrayList;
import java.util.List;

public class IndexCardCategory {

    int id = 0;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
