package com.kamilkirstein.indexcards.dto;

import java.util.Date;

public class IndexCard {

    int id = 0;
    String name;
    String answer;
    String question;
    int categoryId = 0;
    int rating = 0;
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

    public void setId(int id) {
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
