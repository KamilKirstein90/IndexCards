package com.kamilkirstein.indexcards.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kamilkirstein.indexcards.Repository;
import com.kamilkirstein.indexcards.dto.IndexCard;

import java.util.List;

public class IndexCardViewModel extends AndroidViewModel {

    private Repository mRepository;

    public IndexCardViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
    }

    public void insertIndexCard(IndexCard card){
        mRepository.insertUpdateIndexCard(card);
    }

    public LiveData<IndexCard> readIndexCard(int id){
        return mRepository.getOneIndexCard(id);
    }

    public LiveData<List<IndexCard>> queryAllIndexCards(){
        return mRepository.getAllIndexCards();
    }

    public LiveData<List<IndexCard>> queryLastTenIndexCards(){
        return mRepository.getLastTenIndexCards();
    }

}
