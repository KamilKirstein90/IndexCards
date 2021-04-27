package com.kamilkirstein.indexcards.dto;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "indexcard_table")
public class IndexCard {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name ="id")
    int id = 0;

    @NonNull
    @ColumnInfo(name ="name")
    String name;

    @NonNull
    @ColumnInfo(name ="answer")
    String answer;

    @NonNull
    @ColumnInfo(name ="question")
    String question;

    //TODO @ForeignKey()
    @NonNull
    @ColumnInfo(name ="categoryId")
    int categoryId = 0;

    @ColumnInfo(name ="rating")
    int rating = 0;

    //TODO room don´t know how to safe a date field into the db ...
    @NonNull
    @ColumnInfo(name ="timeStamp")
    Date timeStamp;

    //TODO image class for answer and question will be implemented later
    // Image answerImg
    // Image questionImg

    public IndexCard() {
        clearData();
    }

    public void clearData(){
        id = 0;
        name = "";
        answer = "";
        question = "";
        categoryId = 0;
        rating = 0;
        timeStamp = null;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getTimeStamp() { return timeStamp; }

    public void setTimeStamp(Date timeStamp) { this.timeStamp = timeStamp; }
}
